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

	        if (user instanceof RegisteredClient client) {
	        	NotificationsSettingsClientP panelAjustes = new NotificationsSettingsClientP(volver);
	            NotificationSettings settings = client.getNotificationHistory().getSettings();
	            
	            enlazarCheck(panelAjustes.getDisc(), NotificationType.DISCOUNT, settings);
	    	    enlazarCheck(panelAjustes.getOffers(), NotificationType.EXCHANGE, settings);
	    	    enlazarCheck(panelAjustes.getNewSecondHand(), NotificationType.NEW_SECONDHAND_PRODUCT, settings);
	            enlazarCheck(panelAjustes.getOrderState(), NotificationType.ORDER, settings);
	            enlazarCheck(panelAjustes.getPackCart(), NotificationType.PACK_CART, settings);
	            enlazarCheck(panelAjustes.getProductCart(), NotificationType.PRODUCT_CART, settings);
	            enlazarCheck(panelAjustes.getPayment(), NotificationType.PAYMENT, settings);
	            
	            panelAjustes.getDisc().addItemListener(e -> {
	                boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
	                settings.changeInterest(NotificationType.DISCOUNT, estado);
	            });

	            panelAjustes.getOffers().addItemListener(e -> {
	                boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
	                settings.changeInterest(NotificationType.EXCHANGE, estado);
	            });
	            
	            panelAjustes.getNewSecondHand().addItemListener(e -> {
	                boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
	                settings.changeInterest(NotificationType.NEW_SECONDHAND_PRODUCT, estado);
	            });
	            
	            panelAjustes.getOrderState().addItemListener(e -> {
	                boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
	                settings.changeInterest(NotificationType.ORDER, estado);
	            });
	            
	            panelAjustes.getPackCart().addItemListener(e -> {
	                boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
	                settings.changeInterest(NotificationType.PACK_CART, estado);
	            });
	            
	            panelAjustes.getProductCart().addItemListener(e -> {
	                boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
	                settings.changeInterest(NotificationType.PRODUCT_CART, estado);
	            });
	            
	            panelAjustes.getPayment().addItemListener(e -> {
	                boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
	                settings.changeInterest(NotificationType.PAYMENT, estado);
	            });
	            
	            configurarChecksBloqueados(panelAjustes);
	            vista.setDetallePanel(panelAjustes);
	            
	        } else if (user instanceof Employee) {
	        	NotificationsSettingsEmployeeP panelAjustes = new NotificationsSettingsEmployeeP(volver);
	            bloquearAjustesParaEmpleado(panelAjustes);
	        } else
	        	return;

	    }
	    
	    private void bloquearAjustesParaEmpleado(NotificationsSettingsEmployeeP panelAjustes) {
	    	JCheckBox[] todosLosChecks = {
	    	        panelAjustes.getValuation(), panelAjustes.getExchanges(), panelAjustes.getOrders()
	    	    };

	    	    for (JCheckBox check : todosLosChecks) {
	    	        //Quitamos cualquier listener previo
	    	        for (ActionListener al : check.getActionListeners()) check.removeActionListener(al);
	    	        
	    	        check.addActionListener(e -> {
	    	            // Revertimos el estado visual inmediatamente
	    	            check.setSelected(!check.isSelected()); 
	    	            
	    	            // Mostramos el mensaje de error
	    	            JOptionPane.showMessageDialog(vista, 
	    	                "Acción no autorizada: Los empleados no pueden modificar los ajustes de notificación.", 
	    	                "Permiso Denegado", 
	    	                JOptionPane.ERROR_MESSAGE);
	    	        });
	    	        
	    	        check.setEnabled(false); // Si prefieres que ni siquiera puedan clicar, usa esto.
	    	    }
			
		}

		private void enlazarCheck(JCheckBox check, NotificationType tipo, NotificationSettings settings) {
	        //Sincronizar estado inicial
	        check.setSelected(settings.getInterests().getOrDefault(tipo, false));
	        
	        //Crear evento de guardado automático
	        check.addItemListener(e -> {
	            settings.changeInterest(tipo, e.getStateChange() == ItemEvent.SELECTED);
	        });
	    }
	    
	    private void configurarChecksBloqueados(NotificationsSettingsClientP panel) {
	        
	        // Listener para Payment
	        panel.getPayment().addActionListener(e -> {
	            JCheckBox source = (JCheckBox) e.getSource();
	            
	            // Revertimos el estado (si estaba marcado, lo dejamos marcado)
	            source.setSelected(true); 
	            
	            // Lanzamos el aviso
	            JOptionPane.showMessageDialog(vista, 
	                "The notifications of your payments must be active to assure your account's security. ", 
	                "Non-permitted action", 
	                JOptionPane.WARNING_MESSAGE);
	        });

	        // Listener para Order State
	        panel.getOrderState().addActionListener(e -> {
	            JCheckBox source = (JCheckBox) e.getSource();
	            source.setSelected(true);
	            
	            JOptionPane.showMessageDialog(vista, 
	                "You can't disable the tracking of your active orders. ", 
	                "Non-permitted action", 
	                JOptionPane.WARNING_MESSAGE);
	        });
	    }
	    
	}

