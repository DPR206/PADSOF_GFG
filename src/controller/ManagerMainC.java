package controller;

import model.store.Store;
import view.App;
import view.ManagerMainP;

public class ManagerMainC {
    private final ManagerMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getManagerMainPanel();
        this.model = model;
    }
}