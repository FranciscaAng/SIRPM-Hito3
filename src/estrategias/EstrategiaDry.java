package estrategias;
import modelo.OrdenEnvio;

public class EstrategiaDry implements EstrategiaProcesamiento {
    private static final double tarifaKG = 100;
    private static final double tarifaVol = 5000;
    @Override
    public boolean validarOrden(OrdenEnvio orden) {
        return true;
    }
    @Override
    public double calcularTarifa(OrdenEnvio orden) {
        return orden.getPeso() * tarifaKG + orden.getVolumen() * tarifaVol;
    }
    @Override
    public String obtenerRuta(OrdenEnvio orden) {
        return "Ruta estándar";
    }
    @Override
    public String getNombreEstrategia() {
        return "Dry";
    }
}