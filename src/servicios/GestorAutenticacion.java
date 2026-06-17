package servicios;
import java.util.HashMap;
import java.util.Map;

public class GestorAutenticacion {
    private static final Map<String, String> usuarios = new HashMap<>();
    static {
        usuarios.put("admin", "admin123");
        usuarios.put("operador", "operador123");
    }
    public static boolean autenticar(String usuario, String password) {
        String passGuardada = usuarios.get(usuario);
        return passGuardada != null && passGuardada.equals(password);
    }
}