package utilidades;
import modelo.OrdenEnvio;
import java.util.ArrayList;
import java.util.List;

public class ResultadoValidacionCSV {
    public int totalFilas = 0;
    public int ordenesValidas = 0;
    public int ordenesError = 0;
    public List<String> errores = new ArrayList<>();
    public List<OrdenEnvio> ordenes = new ArrayList<>();
}