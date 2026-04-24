package controller;

import model.store.Store;
import view.App;
import view.RegisteredMainP;

public class RegisteredMainC {
    private final RegisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getRegisteredMainPanel();
        this.model = model;
    }
}