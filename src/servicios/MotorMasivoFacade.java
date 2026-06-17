package servicios;

import dao.ResumenMasivoDAO;
import modelo.ResumenMasivo;
import java.io.File;

public class MotorMasivoFacade {
    public ResumenMasivo procesarArchivoMasivo(String rutaArchivo) throws Exception {
        ejecutarMotorCpp(rutaArchivo);
        String rutaResumen = System.getProperty("user.dir") + File.separator + "resumen_sirpm.csv";
        ResumenMasivoDAO dao = new ResumenMasivoDAO();
        return dao.leerResumen(rutaResumen);
    }

    private void ejecutarMotorCpp(String rutaArchivo) throws Exception {
        String rutaExe = System.getProperty("user.dir") + File.separator + "procesador_masivo.exe";

        System.out.println("---- DEBUG SIRPM ----");
        System.out.println("Intentando ejecutar: " + rutaExe);
        System.out.println("Archivo a procesar: " + rutaArchivo);
        System.out.println("---------------------");

        ProcessBuilder pb = new ProcessBuilder(rutaExe, rutaArchivo);
        pb.redirectErrorStream(true);
        Process proceso = pb.start();
        int codigoSalida = proceso.waitFor();
        
        if (codigoSalida != 0) {
            throw new RuntimeException("Error ejecutando motor C++ (Código de salida: " + codigoSalida + ")");
        }
    }
}