package view;

import java.awt.*;
import java.time.LocalDateTime;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		
		Container contenedor = nots.getContentPane();
		contenedor.setLayout(new BorderLayout(10, 10));
		
		JLabel etiqueta = new JLabel("Selecciona una notificación", SwingConstants.CENTER);
		NotificationPayment np = new NotificationPayment(LocalDateTime.now(), false, true, NotificationType.PAYMENT);
		np.FullNotification("pedido2");
		Notification[] notifications = {np,np, np, np, np, np,np, np, np, np, np,np, np, np, np, np,np, np, np, np,
				np,np, np, np, np, np,np, np, np, np, np,np, np, np, np, np,np, np, np, np};
		
		JList<Notification> lista = new JList<>(notifications);
		
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
	                // Aquí usamos el método Snippet() para el texto visual de la lista
	                String textoHtml = "<html>" + n.Snippet().replace("\n", "<br>") + "</html>";
	                setText(textoHtml);
	                //setText(n.Snippet());
	            }
	            return this;
	        }
	    });

	   
	    lista.addListSelectionListener(new ListSelectionListener() {
	        public void valueChanged(ListSelectionEvent ev) {
	            if (!ev.getValueIsAdjusting()) {
	                Notification valorSeleccionado = lista.getSelectedValue();
	                
	                if (valorSeleccionado != null) {
	                    
	                    // Muestra el toString() en el diálogo
	                	//Esto pasará  ser una pantalla nueva
	                    JOptionPane.showMessageDialog(nots, "Detalles completos:\n" + valorSeleccionado.toString());
	                    valorSeleccionado.setRead(true);
	                }
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
				
				contenedor.add(etiqueta, BorderLayout.NORTH); // Título arriba
			    contenedor.add(scroll, BorderLayout.CENTER);  // La lista ocupa el resto

			    // 5. Ajustamos el tamaño de la ventana
			    nots.setSize(300, 450); 
			    nots.setLocationRelativeTo(null); // Centra la ventana en pantalla
			    nots.setVisible(true);

	}
}
