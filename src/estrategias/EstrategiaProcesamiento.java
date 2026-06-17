package estrategias;
import modelo.OrdenEnvio;

public interface EstrategiaProcesamiento {
    boolean validarOrden(OrdenEnvio orden);
    double calcularTarifa(OrdenEnvio orden);
    String obtenerRuta(OrdenEnvio orden);
    String getNombreEstrategia();
}