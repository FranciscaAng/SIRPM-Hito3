package modelo;

public class OrdenEnvio {
    private String id;
    private String origen;
    private String destino;
    private double peso;
    private double volumen;
    private String tipoCarga;
    private boolean certInt;
    private double tempRequerida;
    private int diasTransito;

    public OrdenEnvio() {
    }

    public OrdenEnvio(String id, String origen, String destino, double peso, double volumen, String tipoCarga, boolean certInt, double tempRequerida, int diasTransito) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.volumen = volumen;
        this.tipoCarga = tipoCarga;
        this.certInt = certInt;
        this.tempRequerida = tempRequerida;
        this.diasTransito = diasTransito;
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getOrigen() {return origen;}
    public void setOrigen(String origen) {this.origen = origen;}

    public String getDestino() {return destino;}
    public void setDestino(String destino) {this.destino = destino;}

    public double getPeso() {return peso;}
    public void setPeso(double peso) {this.peso = peso;}

    public double getVolumen() {return volumen;}
    public void setVolumen(double volumen) {this.volumen = volumen;}

    public String getTipoCarga() {return tipoCarga;}
    public void setTipoCarga(String tipoCarga) {this.tipoCarga = tipoCarga;}

    public boolean isCertInt() {return certInt;}
    public void setCertInt(boolean certInt) {this.certInt = certInt;}

    public double getTempRequerida() {return tempRequerida;}
    public void setTemperaturaRequerida(double tempRequerida) {this.tempRequerida = tempRequerida;}

    public double getDiasTransito() {return diasTransito;}
    public void setDiasTransito(int diasTransito) {this.diasTransito = diasTransito;}

    @Override
    public String toString() {return "Orden " + id + " | " + tipoCarga + " | " + origen + " -> " + destino;}
}
