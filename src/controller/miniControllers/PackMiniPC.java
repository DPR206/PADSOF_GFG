package controller.miniControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import controller.browserControllers.BigController;
import model.store.Store;
import model.user.RegisteredClient;
import model.user.UnregisteredClient;
import model.user.UserType;
import view.App;
import view.browserPanels.BigView;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

public class PackMiniPC implements ActionListener{
	
	private final PackMiniP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final BigController bigController;
    private final BigView bigView;
    
    public PackMiniPC(App frame, Store model, PackMiniP view, BigController bigController,
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
		if (e.getActionCommand().equals("Add to Cart")) {
            if (frame.getUser().getType() == UserType.REGISTERED_CLIENT) {
                ((RegisteredClient) frame.getUser()).addCart(view.getPack());
            } else if (frame.getUser().getType() == UserType.UNREGISTERED_CLIENT) {
                ((UnregisteredClient) frame.getUser()).addCart(view.getPack());
            }
            JOptionPane.showMessageDialog(frame, view.getPack().getId() + " was added to Cart",
                    "Added To Cart", JOptionPane.INFORMATION_MESSAGE);
            try {
                bigView.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            bigController.updateControllers();
        }
	}
}
