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
        if(e.getActionCommand().equals("Confirmar")) {
        	/*Ahora se mirarán los textos y checkboxes*/
        	
        }
	}  
}
