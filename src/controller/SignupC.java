package controller;

import model.store.Store;
import view.App;
import view.SignupP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupC implements ActionListener {
    private final SignupP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public SignupC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getSignupPanel();
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // DUE
    }
}