package estrategias;
import modelo.OrdenEnvio;

public class EstrategiaHazmat implements EstrategiaProcesamiento {
    private static final double tarifa = 180;
    private static final double recargo = 300000;
    @Override
    public boolean validarOrden(OrdenEnvio orden) {
        return orden.isCertInt();
    }
    @Override
    public double calcularTarifa(OrdenEnvio orden) {
        return orden.getPeso() * tarifa + recargo;
    }
    @Override
    public String obtenerRuta(OrdenEnvio orden) {
        return "Ruta segura";
    }
    @Override
    public String getNombreEstrategia() {
        return "Hazmat";
    }
}