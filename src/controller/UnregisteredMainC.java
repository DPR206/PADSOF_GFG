package controller;

import model.store.Store;
import model.user.UnregisteredClient;
import model.user.User;
import view.App;
import view.UnregisteredMainP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.BadLocationException;

public class UnregisteredMainC implements ActionListener {
    private final UnregisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final User c = new UnregisteredClient(false);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getUnregisteredMainPanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals("Filters")) {
    	   view.getBrowsePanel().setVisible(false);
           view.getFilterPanel().setVisible(true);
       }
       else if(e.getActionCommand().equals("Search")) {
    	   view.getFilterPanel().setVisible(false);
           view.getBrowsePanel().setVisible(true);
           try {
			view.getBrowsePanel().paintEverything();
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       }
    }
}