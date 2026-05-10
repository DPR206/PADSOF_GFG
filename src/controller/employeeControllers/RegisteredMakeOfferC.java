package controller.employeeControllers;

import controller.Controller;
import controller.browserControllers.BrowseSecondHandProductsC;
import controller.browserControllers.BrowseWalletOwnersC;
import model.store.Store;
import view.App;
import view.clientPanels.RegisteredMakeOfferP;

import javax.swing.text.BadLocationException;

public class RegisteredMakeOfferC implements Controller {
    private final RegisteredMakeOfferP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMakeOfferC(App frame, Store model, RegisteredMakeOfferP view) throws BadLocationException {
        this.frame = frame;
        this.view = view;
        this.model = model;

        initializeActions();
        initializeActionsForMiniPanels();
    }

    public void initializeActionsForMiniPanels() throws BadLocationException {
        new BrowseWalletOwnersC(frame, view.getBrowseWalletOwnersP(), model);
        new BrowseSecondHandProductsC(frame, view.getBrowseSecondHandProductsP(), model);
    }

    @Override
    public void initializeActions() {
        view.getBrowseAvailableProducts().addActionListener(e -> {
            view.getBrowseWalletOwnersP().setVisible(false);
            view.getBrowseSecondHandProductsP().setVisible(true);
            try {
                view.getBrowseSecondHandProductsP().setCurrentPageNum(1);
                initializeActionsForMiniPanels();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getBrowseUsers().addActionListener(e -> {
            view.getBrowseSecondHandProductsP().setVisible(false);
            view.getBrowseWalletOwnersP().setVisible(true);
            try {
                view.getBrowseWalletOwnersP().setCurrentPageNum(1);
                initializeActionsForMiniPanels();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

    }
}