package controller.miniControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import controller.browserControllers.BigController;
import model.store.Store;
import view.App;
import view.browserPanels.BigView;
import view.miniPanels.PackMiniEdit;
import view.miniPanels.PackMiniP;

public class PackMiniEditC implements ActionListener{
	
	private App frame;
	private Store model;
	private PackMiniEdit view;
	private BigController bigController;
	private BigView bigView;
	
	 public PackMiniEditC(App frame, Store model, PackMiniEdit view, BigController bigController,
	            BigView bigView) 
	    {
	    	this.frame = frame;
	    	this.view = view;
	    	this.model = model;
	    	this.bigController = bigController;
	    	this.bigView = bigView;

	    	view.getPackImage().addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent e) {
	    			if (e.getClickCount() == 2) {
	    				JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
	    			}
	    		}
	    	});

	    	view.getPackInfo().addMouseListener(new MouseAdapter() {
	    		public void mouseClicked(MouseEvent e) {
	    			if (e.getClickCount() == 2) {
	    				JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
	    			}
	    		}
	    	});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Gestionar")){
			//MOSTRAR EL PACK, DUE
		}
		
	}
}
