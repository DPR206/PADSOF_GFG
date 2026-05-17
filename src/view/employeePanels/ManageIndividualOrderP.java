package view.employeePanels;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.order.Order;

import javax.swing.*;
import java.awt.*;

public class ManageIndividualOrderP extends JPanel {
    private Order order;
    private JLabel state;
    private BrowseInOrder browser;
    
    // ATRIBUTOS: Botones de estado accesibles para el controlador
    public JButton btnPrep;
    public JButton btnListo;
    public JButton btnRecogido;

    public ManageIndividualOrderP(Order order) {
        this.order = order;
        
        this.setLayout(new BorderLayout());
        
        // Inicialización de tus componentes existentes
        this.state = new JLabel("Estado del Pedido", SwingConstants.CENTER);
        this.browser = new BrowseInOrder();
        
        this.add(state, BorderLayout.NORTH);
        this.add(browser, BorderLayout.CENTER);
        
        // --- PANEL DE ESTADOS (ZONA SUR) ---
        JPanel panelEstados = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelEstados.setBackground(new Color(245, 242, 235)); // Fondo claro

        // Inicializamos los atributos de la clase con su texto HTML
        btnPrep = new JButton("<html><center>EN<br>PREPARACIÓN</center></html>");
        btnListo = new JButton("<html><center>LISTO PARA<br>RECOGER</center></html>");
        btnRecogido = new JButton("RECOGIDO");

        Dimension tam = new Dimension(150, 50);
        Color negro = new Color(30, 30, 30);

        // Bucle rápido para darles el estilo negro inicial a los tres atributos
        for (JButton btn : new JButton[]{btnPrep, btnListo, btnRecogido}) {
            btn.setPreferredSize(tam);
            btn.setBackground(negro);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
        }

        // Añadir los botones y las flechas en orden al panel inferior
        panelEstados.add(btnPrep);
        panelEstados.add(new JLabel("──────►"));
        panelEstados.add(btnListo);
        panelEstados.add(new JLabel("──────►"));
        panelEstados.add(btnRecogido);

        // Insertar el bloque de estados en el SUR del panel principal
        this.add(panelEstados, BorderLayout.SOUTH);
    }
    public JLabel getState() {
    	return this.state;
    }
    public JButton getPreparado() {
    	return this.btnPrep;
    }
    public JButton getList() {
    	return this.btnListo;
    }
    public JButton getRecogido() {
    	return this.btnRecogido;
    }
    public BrowseInOrder getBrowser() {
    	return this.browser;
    }
}