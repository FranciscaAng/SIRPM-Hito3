package estrategias;
import modelo.OrdenEnvio;

public class EstrategiaReefer implements EstrategiaProcesamiento {
    private static final double tarifaKG = 140;
    private static final double costoDia = 50000;
    @Override
    public boolean validarOrden(OrdenEnvio orden) {
        return orden.getTempRequerida() >= -25 && orden.getTempRequerida() <= 5;
    }
    @Override
    public double calcularTarifa(OrdenEnvio orden) {
        return orden.getPeso() * tarifaKG + orden.getDiasTransito() * costoDia;
    }
    @Override
    public String obtenerRuta(OrdenEnvio orden) {
        return "Ruta rápida";
    }
    @Override
    public String getNombreEstrategia() {
        return "Reefer";
    }
}