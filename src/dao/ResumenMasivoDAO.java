package dao;

import modelo.ResumenMasivo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResumenMasivoDAO {
    public ResumenMasivo leerResumen(String rutaArchivo)
            throws IOException {
        int hazmat = 0;
        int reefer = 0;
        int dry = 0;
        int errores = 0;
        int total = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length != 2)
                    continue;
                String clave = datos[0].trim();
                int valor =
                        Integer.parseInt(datos[1].trim());
                switch (clave) {
                    case "Hazmat":
                        hazmat = valor;
                        break;
                    case "Reefer":
                        reefer = valor;
                        break;
                    case "Dry":
                        dry = valor;
                        break;
                    case "Errores":
                        errores = valor;
                        break;
                    case "Total":
                        total = valor;
                        break;
                }
            }
        }
        return new ResumenMasivo(hazmat, reefer, dry, errores, total);
    }
}