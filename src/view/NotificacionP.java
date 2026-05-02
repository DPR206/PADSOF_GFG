package view;

import javax.swing.*;
import java.awt.*;
import model.notification.Notification;

public class NotificacionP extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout = new CardLayout();
    private JPanel contenedorPrincipal = new JPanel(cardLayout);
    private DefaultListModel<Notification> modeloLista = new DefaultListModel<>();
    private JList<Notification> lista = new JList<>(modeloLista);
    private JButton btnMarcarLeido = new JButton("Marcar Leído");
    private JButton btnBorrar = new JButton("Borrar");
    private JButton btnAjustes = new JButton("Ajustes");
    private JPanel banner;

    public NotificacionP(JPanel banner) {
        super("Notificaciones");
        this.banner = banner;
        configurarEstructura();
    }

    private void configurarEstructura() {
    	setLayout(new BorderLayout());

        if (banner != null) {
            add(banner, BorderLayout.NORTH);
        }
    	
        // Panel Lista
        JPanel panelLista = new JPanel(new BorderLayout(10, 10));
        lista.setFixedCellHeight(50);
        
        lista.setCellRenderer(new DefaultListCellRenderer() {
	        @Override
	        public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
	                                                      boolean isSelected, boolean cellHasFocus) {
	            // Llamamos al super para mantener los colores de selección
	            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	            
	            if (value instanceof Notification) {
	                Notification n = (Notification) value;
	                
	                String texto = n.Snippet().replace("\n", "<br>");
	                
	                setFont(new Font("SansSerif", Font.PLAIN, 12)); //Forzamos la tipografía

	                if (!n.isRead()) {
	                    setText("<html><b>" + texto + "</b></html>");
	                    setForeground(Color.BLACK); 
	                } else {
	                    setText("<html>" + texto + "</html>");
	                    setForeground(Color.GRAY);
	                }
	            }
	            return this;
	        }
	    });
        
        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10));
        panelBotones.add(btnMarcarLeido);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnAjustes);
        
        JPanel contenedorBotones = new JPanel(new BorderLayout());
	    contenedorBotones.add(panelBotones, BorderLayout.NORTH);

        panelLista.add(new JScrollPane(lista), BorderLayout.CENTER);
        panelLista.add(contenedorBotones, BorderLayout.EAST);

        contenedorPrincipal.add(panelLista, "LISTA");
        add(contenedorPrincipal, BorderLayout.CENTER);
        
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // MÉTODOS PARA EL CONTROLADOR
    public void mostrarPantalla(String nombre) { cardLayout.show(contenedorPrincipal, nombre); }
    
    public void setDetallePanel(JPanel panel) {
        contenedorPrincipal.add(panel, "DETALLE");
        mostrarPantalla("DETALLE");
    }

    public JList<Notification> getLista() { return lista; }
    public DefaultListModel<Notification> getModelo() { return modeloLista; }
    public JButton getBtnMarcarLeido() { return btnMarcarLeido; }
    public JButton getBtnBorrar() { return btnBorrar; }
    public JButton getBtnAjustes() {return btnAjustes; }
    public JPanel getBanner() { return banner; }
    
}