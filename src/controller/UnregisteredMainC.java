package controller;

import model.store.Store;
import model.user.UnregisteredClient;
import model.user.User;
import view.App;
import view.UnregisteredMainP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        //SE ASUME QUE AQUÍ SE DECIDIÓ NO HACER LOGIN Y SE LE HA PASADO UN UNREGISTEREDCLIENT
    }
}