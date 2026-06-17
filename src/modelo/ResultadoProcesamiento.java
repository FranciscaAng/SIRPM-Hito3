package modelo;

public class ResultadoProcesamiento {
    private String idOrden;
    private String tipoCarga;
    private String estrategiaUtil;
    private boolean valido;
    private double tarifa;
    private String rutaAsignada;
    private String mensaje;

    public ResultadoProcesamiento(String idOrden, String tipoCarga, String estrategiaUtil, boolean valido, double tarifa, String rutaAsignada, String mensaje) {
        this.idOrden = idOrden;
        this.tipoCarga = tipoCarga;
        this.estrategiaUtil = estrategiaUtil;
        this.valido = valido;
        this.tarifa = tarifa;
        this.rutaAsignada = rutaAsignada;
        this.mensaje = mensaje;
    }
    public String getIdOrden() {return idOrden;}
    public String getTipoCarga() {return tipoCarga;}
    public String getEstrategiaUtil() {return estrategiaUtil;}
    public boolean isValido() {return valido;}
    public double getTarifa() {return tarifa;}
    public String getRutaAsignada() {return rutaAsignada;}
    public String getMensaje() {return mensaje;}
    @Override
    public String toString() {return idOrden + " | " + tipoCarga + " | " + (valido ? "Aprobada" : "Rechazada");
    }
}