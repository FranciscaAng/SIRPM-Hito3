package vista;
import servicios.GestorAutenticacion;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContra;
    private JLabel lblMsj;
    public VentanaLogin() {
        setTitle("Login SIRPM");
        setSize(320, 220); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(4, 2, 10, 15));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));
        panelPrincipal.add(new JLabel("Usuario:"));
        panelPrincipal.add(txtUsuario = new JTextField());
        panelPrincipal.add(new JLabel("Contraseña:"));
        panelPrincipal.add(txtContra = new JPasswordField());

        JButton btnIng = new JButton("Ingresar");
        JButton btnSal = new JButton("Salir");
        panelPrincipal.add(btnIng);
        panelPrincipal.add(btnSal);

        lblMsj = new JLabel("", SwingConstants.CENTER);
        panelPrincipal.add(lblMsj);
    
        panelPrincipal.add(new JLabel("")); 
        add(panelPrincipal);

        btnIng.addActionListener(e -> intentarLogin());
        btnSal.addActionListener(e -> System.exit(0));
    }
    private void intentarLogin() {
        String usuario = txtUsuario.getText();
        String password = new String(txtContra.getPassword());

        if (GestorAutenticacion.autenticar(usuario, password)) {
            lblMsj.setText("Bienvenido al sistema SIRPM");
            new VentanaPrincipal(usuario).setVisible(true);
            dispose();
        } else {
            lblMsj.setText("Credenciales inválidas");
            txtContra.setText("");
        }
    }
}