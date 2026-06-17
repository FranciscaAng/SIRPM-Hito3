package utilidades;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GeneradorMasivo {
    public static void main(String[] args) {
        String rutaArchivo = "dataset_1GB_sirpm.csv";
        long filasObjetivo = 18_000_000; 
        String cabecera = "id,origen,destino,peso,volumen,tipocarga,certificadointernacional,temperaturarequerida,diastransito\n";
        
        String[] tiposCarga = {"Hazmat", "Reefer", "Dry", "Invalido"};
        String[] puertos = {"Valparaiso", "San Antonio", "Miami", "Shanghai", "Rotterdam"};
        Random rand = new Random();

        System.out.println("Iniciando generación de datos. Esto puede tomar un par de minutos...");
        long tiempoInicio = System.currentTimeMillis();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write(cabecera);
            StringBuilder sb = new StringBuilder();

            for (long i = 1; i <= filasObjetivo; i++) {
                sb.setLength(0);

                String tipo = tiposCarga[rand.nextInt(tiposCarga.length)];
                boolean certificado = rand.nextBoolean();
                
                String peso = String.format("%.1f", 100 + rand.nextDouble() * 900).replace(',', '.');
                String volumen = String.format("%.1f", 10 + rand.nextDouble() * 90).replace(',', '.');

                sb.append("ORD-").append(i).append(",")
                  .append(puertos[rand.nextInt(puertos.length)]).append(",")
                  .append(puertos[rand.nextInt(puertos.length)]).append(",")
                  .append(peso).append(",")
                  .append(volumen).append(",")
                  .append(tipo).append(",")
                  .append(certificado).append(",");

                if (tipo.equals("Reefer")) {
                    String temp = String.format("%.1f", -20 + rand.nextDouble() * 25).replace(',', '.');
                    int dias = 5 + rand.nextInt(20);
                    sb.append(temp).append(",").append(dias);
                } else {
                    sb.append("0.0,0");
                }
                
                sb.append("\n");
                bw.write(sb.toString());

                if (i % 1_000_000 == 0) {
                    System.out.println((i / 1_000_000) + " millones de filas generadas...");
                }
            }

        } catch (IOException e) {
            System.err.println("Error al generar el archivo: " + e.getMessage());
        }

        long tiempoFin = System.currentTimeMillis();
        System.out.println("¡Archivo generado exitosamente!");
        System.out.println("Tiempo total: " + (tiempoFin - tiempoInicio) / 1000 + " segundos.");
    }
}