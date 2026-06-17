package utilidades;
import modelo.OrdenEnvio;

public class ValidadorEntradas {
    public static OrdenEnvio validarYCrearOrden(String id, String origen, String destino, String pesoStr, String volStr, String tipoCarga, boolean certificado, String tempStr, String diasStr) {
        if (id.isEmpty() || origen.isEmpty() || destino.isEmpty()) {
            throw new IllegalArgumentException("Debe ingresar ID, origen y destino.");
        }
        double peso;
        double volumen;
        try {
            peso = Double.parseDouble(pesoStr);
            volumen = Double.parseDouble(volStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El peso y el volumen deben ser valores numéricos válidos.");
        }
        if (peso <= 0 || volumen <= 0) {
            throw new IllegalArgumentException("El peso y volumen deben ser mayores a 0.");
        }

        if (tipoCarga.equals("Hazmat") && !certificado) {
            throw new IllegalArgumentException("Una carga Hazmat requiere certificado internacional.");
        }
        double temp = 0;
        int dias = 0;
        if (tipoCarga.equals("Reefer")) {
            if (tempStr.isEmpty() || diasStr.isEmpty()) {
                throw new IllegalArgumentException("Para carga Reefer, debe ingresar temperatura requerida y días de tránsito.");
            }
            try {
                temp = Double.parseDouble(tempStr);
                dias = Integer.parseInt(diasStr);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("La temperatura y los días de tránsito deben ser numéricos.");
            }
        }
        return new OrdenEnvio(id, origen, destino, peso, volumen, tipoCarga, certificado, temp, dias);
    }
}