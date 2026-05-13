package controller.managerControllers;

import controller.Controller;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerDiscountsP;

public class ManagerDiscountsC implements Controller {
    private final ManagerDiscountsP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerDiscountsC(App frame, Store model, ManagerDiscountsP view) {
        this.frame = frame;
        this.model = model;
        this.view = view;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getCoverage().addActionListener(e -> {
            // view.changeDiscountPanels(view);
        });

        view.getDiscountType().addActionListener(e -> {
            System.out.println("lmao");
        });

    }
}