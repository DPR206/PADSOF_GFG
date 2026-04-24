package controller;

import model.store.Store;
import view.App;
import view.EmployeeMainP;

public class EmployeeMainC {
    private final EmployeeMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public EmployeeMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getEmployeeMainPanel();
        this.model = model;
    }
}