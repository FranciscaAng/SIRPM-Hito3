package modelo;

public class ResumenMasivo {private int totalHazmat; private int totalReefer; private int totalDry; private int totalErrores; private int totalProcesadas;
    public ResumenMasivo(int totalHazmat, int totalReefer, int totalDry, int totalErrores, int totalProcesadas) {
        this.totalHazmat = totalHazmat;
        this.totalReefer = totalReefer;
        this.totalDry = totalDry;
        this.totalErrores = totalErrores;
        this.totalProcesadas = totalProcesadas;
    }

    public int getTotalHazmat() {
        return totalHazmat;
    }
    public int getTotalReefer() {
        return totalReefer;
    }
    public int getTotalDry() {
        return totalDry;
    }
    public int getTotalErrores() {
        return totalErrores;
    }
    public int getTotalProcesadas() {
        return totalProcesadas;
    }

@Override
    public String toString() {
        int exitosas = totalProcesadas - totalErrores;
        return "Órdenes Exitosas: " + exitosas +
               "\nÓrdenes con Errores: " + totalErrores +
               "\n---------------------------" +
               "\nTotal por tipo de carga:" +
               "\n - Hazmat: " + totalHazmat +
               "\n - Reefer: " + totalReefer +
               "\n - Dry: " + totalDry +
               "\n---------------------------" +
               "\nTotal Procesadas: " + totalProcesadas;
    }
}