package controller.maxiPanels;

import controller.Controller;
import view.App;
import view.clientPanels.RegisteredMainP;
import view.maxiPanels.MaxiSecondHandP;

import javax.swing.*;

/**
 * The type Maxi second hand add to offer c.
 * @author Ana O.R.
 * @version 1.0
 */
public class MaxiSecondHandAddToOfferC implements Controller {
    private final MaxiSecondHandP view;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Maxi second hand add to offer c.
     * @param frame the frame
     * @param view  the view
     */
    public MaxiSecondHandAddToOfferC(App frame, MaxiSecondHandP view) {
        this.view = view;
        this.frame = frame;

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