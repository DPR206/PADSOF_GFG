package controller;

import java.awt.event.ItemEvent;

import javax.swing.*;

import model.notification.*;
import model.user.RegisteredClient;
import view.NotificationsSettingsClientP;

public class NotificationsSettingsClientC {

	private NotificationsSettingsClientP vista;
    private RegisteredClient modelo;

    public NotificationsSettingsClientC(NotificationsSettingsClientP vista, RegisteredClient modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vincularEventos();
    }

    private void vincularEventos() {
        NotificationSettings settings = modelo.getNotificationHistory().getSettings();

        enlazarCheck(vista.getDisc(), NotificationType.DISCOUNT, settings);
	    enlazarCheck(vista.getOffers(), NotificationType.EXCHANGE, settings);
	    enlazarCheck(vista.getNewSecondHand(), NotificationType.NEW_SECONDHAND_PRODUCT, settings);
        enlazarCheck(vista.getOrderState(), NotificationType.ORDER, settings);
        enlazarCheck(vista.getPackCart(), NotificationType.PACK_CART, settings);
        enlazarCheck(vista.getProductCart(), NotificationType.PRODUCT_CART, settings);
        enlazarCheck(vista.getPayment(), NotificationType.PAYMENT, settings);
        
        vista.getDisc().addItemListener(e -> {
            boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
            settings.changeInterest(NotificationType.DISCOUNT, estado);
        });

        vista.getOffers().addItemListener(e -> {
            boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
            settings.changeInterest(NotificationType.EXCHANGE, estado);
        });
        
        vista.getNewSecondHand().addItemListener(e -> {
            boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
            settings.changeInterest(NotificationType.NEW_SECONDHAND_PRODUCT, estado);
        });
        
        vista.getOrderState().addItemListener(e -> {
            boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
            settings.changeInterest(NotificationType.ORDER, estado);
        });
        
        vista.getPackCart().addItemListener(e -> {
            boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
            settings.changeInterest(NotificationType.PACK_CART, estado);
        });
        
        vista.getProductCart().addItemListener(e -> {
            boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
            settings.changeInterest(NotificationType.PRODUCT_CART, estado);
        });
        
        vista.getPayment().addItemListener(e -> {
            boolean estado = (e.getStateChange() == ItemEvent.SELECTED);
            settings.changeInterest(NotificationType.PAYMENT, estado);
        });
        
        // Bloqueos de seguridad específicos del cliente
        configurarChecksBloqueados(vista);
    }

    private void enlazarCheck(JCheckBox check, NotificationType tipo, NotificationSettings settings) {
        check.setSelected(settings.getInterests().getOrDefault(tipo, false));
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
