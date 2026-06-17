package vista;
import estrategias.EstrategiaProcesamiento;
import modelo.OrdenEnvio;
import modelo.ResultadoProcesamiento;
import servicios.EstrategiaFactory;
import servicios.ProcesadorOrdenes;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JTextField txtIdOrden, txtOrigen, txtDestino, txtPeso, txtVolumen, txtTemperatura, txtDiasTransito;
    private JComboBox<String> cbTipoCarga;
    private JCheckBox chkCertificado;
    private JTextArea areaResultados;
    public VentanaPrincipal(String usuario) {
        setTitle("SIRPM - Procesamiento de Órdenes Marítimas | Usuario: " + usuario);
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(crearPanelFormulario(), BorderLayout.NORTH);
        add(crearPanelBotones(), BorderLayout.CENTER);
        add(crearPanelResultados(), BorderLayout.SOUTH);
    }
    private JPanel crearPanelFormulario() {
        JPanel panel = new JPanel(new GridLayout(9, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("ID Orden:"));
        panel.add(txtIdOrden = new JTextField());
        panel.add(new JLabel("Origen:"));
        panel.add(txtOrigen = new JTextField());
        panel.add(new JLabel("Destino:"));
        panel.add(txtDestino = new JTextField());
        panel.add(new JLabel("Peso KG:"));
        panel.add(txtPeso = new JTextField());
        panel.add(new JLabel("Volumen M3:"));
        panel.add(txtVolumen = new JTextField());
        panel.add(new JLabel("Tipo de carga:"));
        panel.add(cbTipoCarga = new JComboBox<>(new String[]{"Hazmat", "Reefer", "Dry"}));
        panel.add(new JLabel("Certificado:"));
        panel.add(chkCertificado = new JCheckBox("Posee certificado internacional"));
        panel.add(new JLabel("Temperatura °C:"));
        panel.add(txtTemperatura = new JTextField());
        panel.add(new JLabel("Días tránsito:"));
        panel.add(txtDiasTransito = new JTextField());
        return panel;
    }
private JPanel crearPanelBotones() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel panelIzquierdo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnProcesar = new JButton("Procesar Orden");
        JButton btnLimpiar = new JButton("Limpiar");
        panelIzquierdo.add(btnProcesar);
        panelIzquierdo.add(btnLimpiar);

        JPanel panelDerecho = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCargarMasiva = new JButton("Módulo Carga Masiva (C++)");
        
        btnCargarMasiva.setFont(new Font("SansSerif", Font.BOLD, 12)); 
        JButton btnSalir = new JButton("Salir");

        panelDerecho.add(btnCargarMasiva);
        panelDerecho.add(btnSalir);

        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        btnSalir.addActionListener(e -> System.exit(0));
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnProcesar.addActionListener(e -> capturarYProcesarOrden());
        btnCargarMasiva.addActionListener(e -> new VentanaCargaMasiva().setVisible(true));

        return panelPrincipal;
    }
    private JScrollPane crearPanelResultados() {
        areaResultados = new JTextArea(15, 40);
        areaResultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultados);
        scroll.setBorder(BorderFactory.createTitledBorder("Resultado:"));
        return scroll;
    }
    private void limpiarFormulario() {
        txtIdOrden.setText("");
        txtOrigen.setText("");
        txtDestino.setText("");
        txtPeso.setText("");
        txtVolumen.setText("");
        cbTipoCarga.setSelectedIndex(0);
        chkCertificado.setSelected(false);
        txtTemperatura.setText("");
        txtDiasTransito.setText("");
        areaResultados.setText("");
    }
    private void capturarYProcesarOrden() {
        try {
            OrdenEnvio orden = utilidades.ValidadorEntradas.validarYCrearOrden(
                txtIdOrden.getText().trim(),
                txtOrigen.getText().trim(),
                txtDestino.getText().trim(),
                txtPeso.getText().trim(),
                txtVolumen.getText().trim(),
                (String) cbTipoCarga.getSelectedItem(),
                chkCertificado.isSelected(),
                txtTemperatura.getText().trim(),
                txtDiasTransito.getText().trim()
            );
            EstrategiaProcesamiento estrategia = EstrategiaFactory.crearEstrategia(orden.getTipoCarga());
            ProcesadorOrdenes procesador = new ProcesadorOrdenes();
            procesador.setEstrategia(estrategia);

            ResultadoProcesamiento resultado = procesador.procesarOrden(orden);
            mostrarResultado(orden, resultado);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void mostrarResultado(OrdenEnvio orden, ResultadoProcesamiento res) {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------------------------------------\n");
        sb.append("Orden: ").append(res.getIdOrden()).append("\n");
        sb.append("Tipo: ").append(res.getTipoCarga()).append("\n");
        sb.append("Estrategia utilizada: Estrategia").append(res.getEstrategiaUtil()).append("\n");
        sb.append("Validación: ").append(res.isValido() ? "Aprobada" : "Rechazada").append("\n");
        sb.append("Tarifa calculada: $").append(String.format("%,.0f", res.getTarifa())).append("\n\n");
        sb.append("Ruta asignada: ").append(orden.getOrigen()).append(" -> ").append(res.getRutaAsignada()).append(" -> ").append(orden.getDestino()).append("\n");
        sb.append("Mensaje: ").append(res.getMensaje()).append("\n");
        sb.append("--------------------------------------------------\n");
        areaResultados.setText(sb.toString());
    }
}