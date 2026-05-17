package controller.maxiPanels;

import controller.Controller;
import controller.clientControllers.RegisteredMakeOfferC;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.RegisteredMakeOfferP;
import view.maxiPanels.MaxiSecondHandP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The type Maxi second hand add to offer c.
 * @author Ana O.R.
 * @version 1.0
 */
public class MaxiSecondHandAddToOfferC implements Controller {
    private final MaxiSecondHandP view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Maxi second hand add to offer c.
     * @param frame the frame
     * @param view  the view
     */
    public MaxiSecondHandAddToOfferC(App frame, MaxiSecondHandP view, Store model) {
        this.view = view;
        this.frame = frame;
        this.model = model;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getButton().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, view.getProduct().getName() + " was added to the Offer",
                    "Added To Offer", JOptionPane.INFORMATION_MESSAGE);

            try {
                if (view.getProduct().getOwner() != frame.getUser()) {
                    RegisteredMakeOfferP registeredMakeOfferP =
                            new RegisteredMakeOfferP(frame, view.getProduct().getOwner(),
                                    (RegisteredClient) frame.getUser());
                    new RegisteredMakeOfferC(frame, model, registeredMakeOfferP, view.getProduct().getOwner());

                    frame.addProductFromTheirWallet(view.getProduct());

                    JPanel check = frame.getViewFromName("MAKE OFFER");
                    if (check != null) {
                        frame.remove(check);
                    }
                    frame.addCard(registeredMakeOfferP, "MAKE_OFFER");
                    frame.changeVisibleCard("MAKE_OFFER");
                } else {
                    frame.addProductFromMyWallet(view.getProduct());

                }
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}