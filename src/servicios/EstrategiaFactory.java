package servicios;
import estrategias.*;

public class EstrategiaFactory {
    public static EstrategiaProcesamiento crearEstrategia(String tipoCarga) {
        if (tipoCarga.equalsIgnoreCase("Hazmat")) {
            return new EstrategiaHazmat();
        }
        if (tipoCarga.equalsIgnoreCase("Reefer")) {
            return new EstrategiaReefer();
        }
        return new EstrategiaDry();
    }
}