package controller;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import model.notification.NotificationSettings;
import model.notification.NotificationType;
import model.user.Employee;
import view.NotificationsSettingsEmployeeP;

public class NotificationsSettingsEmployeeC {

	private NotificationsSettingsEmployeeP vista;
	private Employee modelo;
	
	public NotificationsSettingsEmployeeC(NotificationsSettingsEmployeeP vista, Employee modelo) {
        this.vista = vista;
        this.modelo = modelo;
        bloquearAjustesParaEmpleado(vista);
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
	    	        
	    	        check.setEnabled(false); 
	    	    }
			
		}
	 
}
