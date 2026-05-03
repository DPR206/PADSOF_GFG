package controller;

import view.*;
import model.notification.*;
import model.user.*;
import java.awt.event.*;

import javax.swing.*;

public class NotificacionesC {
	
		//private App frame;
	    private NotificacionP vista;
	    private User user;

	    public NotificacionesC (NotificacionP vista, App frame) {
	        this.vista = vista;
	        this.user = frame.getUser();
	        //this.frame = frame;
	        
	        /*if (user instanceof Employee) {
	            vista.getBtnAjustes().setVisible(false);
	        } else {
	            vista.getBtnAjustes().setVisible(true);
	        }*/
	        vista.getBtnAjustes().setVisible(true);
	        cargarNotificacionesDelUsuario();
	        inicializarEventos();
	    }
	    
	    private void cargarNotificacionesDelUsuario() {
	        // Limpiamos el modelo de la vista
	        vista.getModelo().clear();

	        // Obtenemos el historial del usuario y lo filtramos por visibilidad
	        if(user instanceof RegisteredClient client)
	        	client.browseNotifications().forEach(n -> {
	        	if(n.isVisible()) {
	        		vista.getModelo().addElement(n);
	        	}
	        });
	        else if(user instanceof Employee employee)
	        	employee.browseNotifications().forEach(n -> {
	        	if(n.isVisible()) {
	        		vista.getModelo().addElement(n);
	        	}
	        });
	    }

	    private void inicializarEventos() {
	        // Evento Marcar Leído
	        vista.getBtnMarcarLeido().addActionListener(e -> {
	            Notification sel = vista.getLista().getSelectedValue();
	            if (sel != null) {
	                sel.setRead(true);
	                vista.getLista().repaint();
	            }
	        });

	        // Evento Doble Clic (Abrir Detalle)
	        vista.getLista().addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2) {
	                    Notification sel = vista.getLista().getSelectedValue();
	                    if (sel != null) {
	                        sel.setRead(true);
	                        abrirDetalle(sel);
	                    }
	                }
	            }
	        });
	        
	        //Evento borrar
	        vista.getBtnBorrar().addActionListener(e -> {
	            Notification sel = vista.getLista().getSelectedValue();
	            if (sel != null) {
	            	sel.setVisible(false); 
	                vista.getModelo().removeElement(sel);
	            }
	        });
	        
	        //Permitir borrar pulsando la tecla "Suprimir" (Delete)
	        vista.getLista().addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyPressed(KeyEvent e) {
		            if (e.getKeyCode() == KeyEvent.VK_DELETE) {
		                vista.getBtnBorrar().doClick(); 
		            }
		        }
		    });
	        
	        vista.getBtnAjustes().addActionListener(e -> abrirAjustes());
	        
	    }

	    private void abrirDetalle(Notification n) {
	        // Creamos la vista de detalle y le pasamos la acción de volver
	    	NotificationDetailP vistaDetalle = new NotificationDetailP(n, e -> {
	            vista.mostrarPantalla("LISTA");
	            vista.getLista().repaint();
	        });
	        
	        vista.setDetallePanel(vistaDetalle);
	    }
	    
	    private void abrirAjustes() {
	    	ActionListener volver = e -> vista.mostrarPantalla("LISTA");
	        JPanel bannerParaAjustes = vista.getBanner();

	        if (user instanceof RegisteredClient client) {
	            NotificationsSettingsClientP vistaC = new NotificationsSettingsClientP(volver, bannerParaAjustes);
	            new NotificationsSettingsClientC(vistaC, client); // Controlador de cliente
	            vista.setDetallePanel(vistaC);
	        } 
	        else if (user instanceof Employee employee) {
	            NotificationsSettingsEmployeeP vistaE = new NotificationsSettingsEmployeeP(volver, bannerParaAjustes);
	            new NotificationsSettingsEmployeeC(vistaE, employee); // Controlador de empleado
	            vista.setDetallePanel(vistaE);
	        } else
	        	return;

	    }
	    
}