package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.BrowseSecondHandProductsForOfferC;
import controller.browserControllers.BrowseWalletOwnersC;
import model.store.Store;
import view.App;
import view.clientPanels.RegisteredBrowseForOfferP;

import javax.swing.text.BadLocationException;

/**
 * The type Registered make offer c.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredBrowseForOfferC implements Controller {
    private final RegisteredBrowseForOfferP view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered make offer c.
     * @param frame the frame
     * @param model the model
     * @param view  the view
     */
    public RegisteredBrowseForOfferC(App frame, Store model, RegisteredBrowseForOfferP view) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        initializeActions();
    }

    /**
     * Initialize actions for mini panels.
     * @throws BadLocationException the bad location exception
     */
    public void initializeActionsForMiniPanels() throws BadLocationException {
        new BrowseWalletOwnersC(frame, view.getBrowseWalletOwnersP(), model);
        new BrowseSecondHandProductsForOfferC(frame, view.getBrowseSecondHandProductsP(), model);
    }

    @Override
    public void initializeActions() {
        try {
            initializeActionsForMiniPanels();
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        view.getBrowseAvailableProducts().addActionListener(e -> {
            view.getBrowseWalletOwnersP().setVisible(false);
            view.getBrowseSecondHandProductsP().setVisible(true);
            try {
                initializeActionsForMiniPanels();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getBrowseUsers().addActionListener(e -> {
            view.getBrowseSecondHandProductsP().setVisible(false);
            view.getBrowseWalletOwnersP().setVisible(true);
            try {
                initializeActionsForMiniPanels();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}