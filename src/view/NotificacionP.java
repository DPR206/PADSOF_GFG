package view;

import java.awt.*;
import java.time.LocalDateTime;

import java.awt.event.*;

import javax.swing.*;

import model.notification.*;

public class NotificacionP extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notificacion frame = new Notificacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
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
		NotificationPayment np = new NotificationPayment(LocalDateTime.now(), false, true, NotificationType.PAYMENT);
		np.FullNotification("pedido2");
		NotificationPayment np2 = new NotificationPayment(LocalDateTime.now(), true, true, NotificationType.PAYMENT);
		np2.FullNotification("pedido1");
		Notification[] notifications = {np, np, np2, np, np, np, np, np, np2, np, np, np, np2, 
				np, np, np2, np, np, np, np, np, np2, np, np, np, np2};
		
		//Filtramos para que solo se impriman las notificaciones visibles
		for (Notification n : notifications) {
		    if (n.isVisible()) {
		        modeloFiltrado.addElement(n);
		    }
		}

		// Creamos la lista usando el modelo filtrado
		JList<Notification> lista = new JList<>(modeloFiltrado);
		
		//JList<Notification> lista = new JList<>(notifications);
		
		//lista.setVisibleRowCount(10); 
		
	    lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    lista.setFixedCellHeight(50);
		
		/*lista.addListSelectionListener(
				  new ListSelectionListener() {
				    public void valueChanged(ListSelectionEvent ev) {
				    	JList<String> localJList = (JList<String>) ev.getSource();
				    	System.out.println( lista.getSelectedValue() );
				    	if (! ev.getValueIsAdjusting() ) {
				    	  Notification valorSeleccionado = (Notification) lista.getSelectedValue();
				          JOptionPane.showMessageDialog(nots, "Seleccionaste: " + valorSeleccionado);
				    	}
				    }
				  }
				);*/
		
		
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

	   
	    /*lista.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent ev) {
	            if (!ev.getValueIsAdjusting()) {
	                Notification valorSeleccionado = lista.getSelectedValue();
	                
	                if (valorSeleccionado != null) {
	                	
	                	valorSeleccionado.setRead(true);
	                    
	                    // Muestra el toString() en el diálogo
	                	//Esto pasará  ser una pantalla nueva
	                    JOptionPane.showMessageDialog(nots, "Detalles completos:\n" + valorSeleccionado.toString());
	                    
	                    //lista.repaint(); /*Algo no funciona
	                }
	            }
	        }
	    }); */
	    
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
	                    //lista.repaint();
	                }
	            }
	        }
	    });
	    
	    JButton btnBorrar = new JButton("Borrar Seleccionada");
	    btnBorrar.addActionListener(e -> {
	        Notification seleccionada = lista.getSelectedValue();
	        if (seleccionada != null) {
	            seleccionada.setVisible(false); // Tu atributo
	            // Actualizamos el modelo filtrado (el que creamos antes)
	            ((DefaultListModel<Notification>)lista.getModel()).removeElement(seleccionada);
	        }
	    });
	    
	 // Permitir borrar pulsando la tecla "Suprimir" (Delete)
	    lista.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
	                // Ejecutamos la misma lógica que el botón borrar
	                btnBorrar.doClick(); 
	            }
	        }
	    });

		
				// Para que solo se pueda seleccionar una fila
				lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
				        
				// Es aconsejable crear una barra de scroll para la lista,
				// por si el numero de elementos supera el tamanyo previsto 
				JScrollPane scroll = new JScrollPane(lista);
				        
				// Añadir el scroll con la lista al panel donde se vaya a mostrar
				/*JPanel ejemploList = new JPanel();
				ejemploList.add(scroll);*/

				// Añadir componentes al contenedor
				/*contenedor.add(etiqueta);
				contenedor.add(ejemploList);
				ejemploList.setVisible(true);*/

				// mostrar ventana
				/*nots.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				nots.setSize(200,340);
				nots.setVisible(true);*/
				
				JPanel panelBotones = new JPanel();
			    panelBotones.add(btnBorrar);

			    // Añadimos todo al frame principal
			    //contenedor.add(new JLabel("Bandeja de Entrada", SwingConstants.CENTER), BorderLayout.NORTH);
				contenedor.add(etiqueta, BorderLayout.NORTH); // Título arriba
			    contenedor.add(scroll, BorderLayout.CENTER);  // La lista ocupa el resto
			    contenedor.add(panelBotones, BorderLayout.EAST);

			    // 5. Ajustamos el tamaño de la ventana
			    nots.setSize(300, 450); 
			    nots.setLocationRelativeTo(null); // Centra la ventana en pantalla
			    nots.setVisible(true);

	}
}
