package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ManagerNewProduct;

public class ManagerNewProductC implements ActionListener{
	private final ManagerNewProduct panel;
	
	public ManagerNewProductC(ManagerNewProduct panel) {
		this.panel = panel;
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
		String name;
		double price;
		int stock;
		String description;
		
        if(e.getActionCommand().equals("Confirmar")) {
        	if(panel.getNombreField().getText() == null) return;
        	name = panel.getNombreField().getText();
        	if(panel.getPrecioField().getText() == null) return;
        	price = Double.parseDouble(panel.getPrecioField().getText());
        	if(panel.getStockField().getText() == null) return;
        	stock = Integer.parseInt(panel.getStockField().getText());
        	if(panel.getDescArea().getText() == null) return;
        	description = panel.getDescArea().getText();
        	if()
        }
	}  
}
