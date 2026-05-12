package controller.maxiPanels;

import controller.Controller;
import model.store.Store;
import view.App;
import view.clientPanels.RegisteredMainP;
import view.maxiPanels.MaxiSecondHandP;

import javax.swing.*;

public class MaxiSecondHandAddToOfferC implements Controller {
    private final MaxiSecondHandP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public MaxiSecondHandAddToOfferC(App frame, Store model, MaxiSecondHandP view) {
        this.view = view;
        this.frame = frame;
        this.model = model;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getButton().addActionListener(e -> {
            //DUE: Aceptar oferta en función de si el producto es mío o no
            JOptionPane.showMessageDialog(frame, view.getProduct().getName() + " was " + "added to " + "the Offer",
                    "Added To Offer", JOptionPane.INFORMATION_MESSAGE);
            ((RegisteredMainP) frame.getViewFromName("REGISTERED_MAIN")).getCardLayout()
                                                                        .show(((RegisteredMainP) frame.getViewFromName(
                                                                                        "REGISTERED_MAIN")).getBottom(),
                                                                                "Second Hand");
        });
    }
}