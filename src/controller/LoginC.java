package controller;

import model.store.Store;
import view.App;
import view.LoginP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * It implements the log-in controller
 * @author Ana O.R.
 * @version 1.0
 */
public class LoginC implements ActionListener {
    private final LoginP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public LoginC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getLoginPanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // DUE
    }
}