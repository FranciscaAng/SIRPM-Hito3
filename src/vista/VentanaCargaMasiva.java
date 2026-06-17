package vista;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class VentanaCargaMasiva extends JFrame {
    private JLabel lblRutaArchivo;
    private JTextArea txtResumen;
    private File archivoSeleccionado;
    private JButton btnProcesar;

    public VentanaCargaMasiva() {
        setTitle("SIRPM - Carga Masiva de Ordenes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
    }

private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        
        lblRutaArchivo = new JLabel("Archivo seleccionado: [Ninguno]", SwingConstants.CENTER);
        panel.add(lblRutaArchivo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnSeleccionar = new JButton("Seleccionar Archivo");

        btnProcesar = new JButton("Procesar Archivo Masivo");
        btnProcesar.setEnabled(false); 

        btnSeleccionar.addActionListener(e -> seleccionarArchivo());
        btnProcesar.addActionListener(e -> procesarArchivo());

        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnProcesar);
        panel.add(panelBotones, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        txtResumen = new JTextArea();
        txtResumen.setEditable(false);
        txtResumen.setFont(new Font("Consolas", Font.PLAIN, 15)); 
        txtResumen.setBackground(new Color(245, 245, 245));

        JScrollPane scrollResumen = new JScrollPane(txtResumen);
        scrollResumen.setBorder(BorderFactory.createTitledBorder("Métricas Consolidadas (Motor C++):"));

        panel.add(scrollResumen, BorderLayout.CENTER);
        return panel;
    }

private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV", "csv"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            archivoSeleccionado = fileChooser.getSelectedFile();
            lblRutaArchivo.setText("Archivo seleccionado: [" + archivoSeleccionado.getAbsolutePath() + "]");
            
            btnProcesar.setEnabled(true); 
            txtResumen.setText("");
        }
    }

    private void procesarArchivo() {
        if (archivoSeleccionado == null) return;

        btnProcesar.setEnabled(false);

        txtResumen.setText("Procesando millones de registros... por favor espere.\nEsto puede tomar unos momentos.");

        SwingWorker<modelo.ResumenMasivo, Void> worker = new SwingWorker<>() {
            
            @Override
            protected modelo.ResumenMasivo doInBackground() throws Exception {
                servicios.MotorMasivoFacade fachada = new servicios.MotorMasivoFacade();
                return fachada.procesarArchivoMasivo(archivoSeleccionado.getAbsolutePath());
            }

            @Override
            protected void done() {
                try {
                    modelo.ResumenMasivo resumen = get(); 
                    txtResumen.setText("¡Procesamiento completado con éxito!\n\n" + resumen.toString());
                } catch (Exception e) {
                    txtResumen.setText("Ocurrió un error durante el procesamiento nativo:\n" + e.getMessage());
                    e.printStackTrace();
                } finally {
                    btnProcesar.setEnabled(true);
                }
            }
        };
        
        worker.execute();
    }
}