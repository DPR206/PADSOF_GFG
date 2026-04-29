package view;

import java.awt.*;
import java.time.LocalDateTime;

import java.awt.event.*;

import javax.swing.*;

import model.notification.*;

public class NotificacionPrueba extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CardLayout pantallas = new CardLayout();
	private JPanel contenedorPrincipal = new JPanel(pantallas);
	DefaultListModel<Notification> modeloFiltrado;
	JList<Notification> lista;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new NotificacionPrueba());
	}

	/**
	 * Create the frame.
	 */
	public NotificacionPrueba() {
		
		JFrame nots = new JFrame("Notificaciones");
		nots.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    nots.setSize(300, 450);
		modeloFiltrado = new DefaultListModel<>();

		// Creamos 20 notificaciones diferentes
		for (int i = 1; i <= 20; i++) {
		    // Alternamos entre leídas y no leídas para probar el diseño
		    boolean estaLeida = (i % 3 == 0); // Cada 3 notificaciones, una vendrá ya leída
		    
		    NotificationPayment temp = new NotificationPayment(
		        LocalDateTime.now().minusHours(i), // Diferentes horas
		        estaLeida, 
		        true, // Visible
		        NotificationType.PAYMENT
		    );
		    
		    temp.FullNotification("Pedido #" + (1000 + i)); // ID de pedido único
		    
		    //Filtramos para que solo se impriman las notificaciones visibles
			if (temp.isVisible()) {
			    modeloFiltrado.addElement(temp);
			}
			
		}
		
		// 2. CONSTRUIR PANEL DE LISTA
        JPanel panelLista = crearPanelLista();
        
        // 3. CONSTRUIR PANEL DE DETALLE (se llenará dinámicamente)
        JPanel panelDetalle = crearPanelDetalleVacio();

        // Añadir al contenedor de cartas
        contenedorPrincipal.add(panelLista, "PANTALLA_LISTA");
        contenedorPrincipal.add(panelDetalle, "PANTALLA_DETALLE");

        nots.add(contenedorPrincipal);
        nots.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nots.setSize(450, 500);
        nots.setLocationRelativeTo(null);
        nots.setVisible(true);
	}

	private JPanel crearPanelLista() {
		
		JPanel panel = new JPanel(new BorderLayout(10, 10));
        JLabel etiqueta = new JLabel("Notificaciones", SwingConstants.CENTER);
        etiqueta.setFont(new Font("SansSerif", Font.BOLD, 16));
        etiqueta.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        lista = new JList<>(modeloFiltrado);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

        // Evento Doble Clic
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = lista.locationToIndex(e.getPoint());
                    if (index != -1) {
                        Notification n = modeloFiltrado.getElementAt(index);
                        n.setRead(true); //Marcar como leído
                        actualizarYMostrarDetalle(n);
                    }
                }
            }
        });

        // Panel de Botones VERTICAL
        JPanel panelBotones = new JPanel(new GridLayout(0, 1, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 10)); //Dejar márgenes en los laterales de los botones

        JButton btnMarcarLeido = new JButton("Marcar como leída");
        btnMarcarLeido.addActionListener(e -> {
            Notification sel = lista.getSelectedValue();
            if (sel != null) {
                sel.setRead(true);
                lista.repaint();
            }
        });

        JButton btnBorrar = new JButton("Borrar");
        btnBorrar.addActionListener(e -> {
            Notification sel = lista.getSelectedValue();
            if (sel != null) {
                modeloFiltrado.removeElement(sel);
            }
        });
        
     // Permitir borrar pulsando la tecla "Suprimir" (Delete)
	    lista.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
	                btnBorrar.doClick(); 
	            }
	        }
	    });
	    
	    JButton btnAjustes = new JButton("Ajustes");
	    


        panelBotones.add(btnMarcarLeido);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnAjustes);
	    
	    JPanel contenedorBotones = new JPanel(new BorderLayout());
	    contenedorBotones.add(panelBotones, BorderLayout.NORTH);

        panel.add(etiqueta, BorderLayout.NORTH);
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        panel.add(contenedorBotones, BorderLayout.EAST);

        return panel;
	}
	
	private JPanel crearPanelDetalleVacio() {
        // Solo creamos el contenedor, el contenido se genera al hacer clic
        return new JPanel(new BorderLayout());
    }
	
	private void actualizarYMostrarDetalle(Notification n) {
	    // 1. Creamos la acción que queremos que ocurra cuando se pulse "Volver"
	    ActionListener volverAccion = e -> {
	        pantallas.show(contenedorPrincipal, "PANTALLA_LISTA");
	        lista.repaint();
	    };
	    NotificationDetailP panelDetalle = new NotificationDetailP(n, volverAccion);

	    // Reemplazamos el panel en el contenedor principal
	    contenedorPrincipal.add(panelDetalle, "PANTALLA_DETALLE");
	    
	    // Mostramos la pantalla
	    pantallas.show(contenedorPrincipal, "PANTALLA_DETALLE");
	}
}
