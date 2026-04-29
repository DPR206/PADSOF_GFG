package view;

import java.awt.*;
import java.time.LocalDateTime;

import java.awt.event.*;

import javax.swing.*;

import model.notification.*;

public class NotificacionP extends JPanel {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new NotificacionP();
	}

	/**
	 * Create the frame.
	 */
	public NotificacionP() {
		
		JFrame nots = new JFrame("Notificaciones");
		DefaultListModel<Notification> modeloFiltrado = new DefaultListModel<>();
		
		Container contenedor = nots.getContentPane();
		contenedor.setLayout(new BorderLayout(10, 10));
		
		JLabel etiqueta = new JLabel("Selecciona una notificación", SwingConstants.CENTER);

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
	

		// Creamos la lista usando el modelo filtrado
		JList<Notification> lista = new JList<>(modeloFiltrado);
		
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
	    
	    lista.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            // Detectamos si es doble clic
	            if (e.getClickCount() == 2) {
	                // Obtenemos el índice del elemento sobre el que se hizo clic
	                int index = lista.locationToIndex(e.getPoint());
	                
	                if (index != -1) {
	                    Notification seleccionada = lista.getModel().getElementAt(index);
	                    
	                    // 1. Marcar como leída
	                    seleccionada.setRead(true);
	                    
	                    // 2. Abrir la pantalla de detalle
	                    //DetalleNotificacion ventanaDetalle = new DetalleNotificacion(nots, seleccionada);
	                    //ventanaDetalle.setVisible(true);
	                    JOptionPane.showMessageDialog(nots, "Detalles completos:\n" + seleccionada.toString());
	                    
	                    // 3. Refrescar la lista para quitar la negrita
	                    lista.repaint();
	                }
	            }
	        }
	    });
	    
	    JButton btnBorrar = new JButton("Borrar Seleccionada");
	    btnBorrar.addActionListener(e -> {
	        Notification seleccionada = lista.getSelectedValue();
	        if (seleccionada != null) {
	            seleccionada.setVisible(false);
	            // Actualizamos el modelo filtrado
	            ((DefaultListModel<Notification>)lista.getModel()).removeElement(seleccionada);
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
	    
	    JButton btnMarcarLeido = new JButton("Marcar Como Leído Seleccionada");
	    btnMarcarLeido.addActionListener(e -> {
	    	int index = lista.getSelectedIndex();
	        Notification seleccionada = lista.getSelectedValue();
	        if (seleccionada != null) {
	            seleccionada.setRead(true);
	            
	            /*// OPCIÓN A: Forzar repintado visual (Rápido)
        		lista.repaint(); */
        
	            // OPCIÓN B: Notificar al modelo (Elegante)
	            modeloFiltrado.set(index, seleccionada);
	            
	        }
	    });

		
				// Para que solo se pueda seleccionar una fila
				lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
				        
				// Es aconsejable crear una barra de scroll para la lista,
				// por si el numero de elementos supera el tamanyo previsto 
				JScrollPane scroll = new JScrollPane(lista);

				JPanel panelBotones = new JPanel(new GridLayout(0, 1, 5, 5));
			    panelBotones.add(btnBorrar);
			    panelBotones.add(btnMarcarLeido);
			    
			    JPanel contenedorBotones = new JPanel(new BorderLayout());
			    contenedorBotones.add(panelBotones, BorderLayout.NORTH);

			    // Añadimos todo al frame principal
				contenedor.add(etiqueta, BorderLayout.NORTH); // Título arriba
			    contenedor.add(scroll, BorderLayout.CENTER);  // La lista ocupa el resto
			    contenedor.add(contenedorBotones, BorderLayout.EAST); //Botones laterales

			    //Ajustamos el tamaño de la ventana
			    nots.setSize(300, 450); 
			    nots.setLocationRelativeTo(null); // Centrar la ventana en pantalla
			    nots.setVisible(true);

	}
}
