package servicios;
import estrategias.EstrategiaProcesamiento;
import modelo.OrdenEnvio;
import modelo.ResultadoProcesamiento;

public class ProcesadorOrdenes {
    private EstrategiaProcesamiento estrategia;
    public void setEstrategia(EstrategiaProcesamiento estrategia) {
        this.estrategia = estrategia;
    }
    public ResultadoProcesamiento procesarOrden(OrdenEnvio orden) {
        boolean valido = estrategia.validarOrden(orden);
        double tarifa = 0;
        String ruta = "No asignada";
        String mensaje;
        if (valido) {
            tarifa = estrategia.calcularTarifa(orden);
            ruta = estrategia.obtenerRuta(orden);
            mensaje = "Orden procesada correctamente";
        } else {
            mensaje = "Orden rechazada";
        }
        return new ResultadoProcesamiento(orden.getId(), orden.getTipoCarga(), estrategia.getNombreEstrategia(), valido, tarifa, ruta, mensaje);
    }
}