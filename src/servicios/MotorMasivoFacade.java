package servicios;

import dao.ResumenMasivoDAO;
import modelo.ResumenMasivo;
import java.io.File;

public class MotorMasivoFacade {
    public ResumenMasivo procesarArchivoMasivo(String rutaArchivo) throws Exception {
        ejecutarMotorCpp(rutaArchivo);
        File archivoResumen = new File(System.getProperty("java.io.tmpdir"), "resumen_sirpm.csv");
        ResumenMasivoDAO dao = new ResumenMasivoDAO();
        return dao.leerResumen(archivoResumen.getAbsolutePath());
    }

    private void ejecutarMotorCpp(String rutaArchivo) throws Exception {
        String rutaExe = System.getProperty("user.dir") + File.separator + "procesador_masivo.exe";
        ProcessBuilder pb = new ProcessBuilder(rutaExe, rutaArchivo);
        pb.directory(new File(System.getProperty("java.io.tmpdir")));
        pb.redirectErrorStream(true);
        Process proceso = pb.start();
        int codigoSalida = proceso.waitFor();
        
        if (codigoSalida != 0) {
            throw new RuntimeException("Error ejecutando motor C++ (Código de salida: " + codigoSalida + ")");
        }
    }
}