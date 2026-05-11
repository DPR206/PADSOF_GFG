package controller;

import model.store.Store;
import view.App;
import view.employeePanels.MaxiValuateSecondHandP;

public class MaxiValuateSecondHandC implements Controller {
    private final MaxiValuateSecondHandP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public MaxiValuateSecondHandC(App frame, Store model, MaxiValuateSecondHandP view) {
        this.view = view;
        this.frame = frame;
        this.model = model;

        initializeActions();
    }

    @Override
    public void initializeActions() {

    }
}