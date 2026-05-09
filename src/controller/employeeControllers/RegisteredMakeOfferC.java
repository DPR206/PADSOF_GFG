package controller.employeeControllers;

import controller.browserControllers.BrowseSecondHandProductsC;
import controller.browserControllers.BrowseWalletOwnersC;
import model.store.Store;
import view.App;
import view.clientPanels.RegisteredMakeOfferP;

import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisteredMakeOfferC implements ActionListener {
    private final RegisteredMakeOfferP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMakeOfferC(App frame, Store model, RegisteredMakeOfferP view) {
        this.frame = frame;
        this.view = view;
        this.model = model;

        updateControllers();
    }

    public void updateControllers() {
        view.getBrowseWalletOwnersP()
            .setController(new BrowseWalletOwnersC(frame, view.getBrowseWalletOwnersP(), model));
        view.getBrowseSecondHandProductsP()
            .setController(new BrowseSecondHandProductsC(frame, view.getBrowseSecondHandProductsP(), model));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Browse available products")) {
            view.getBrowseWalletOwnersP().setVisible(false);
            view.getBrowseSecondHandProductsP().setVisible(true);
            try {
                view.getBrowseSecondHandProductsP().setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getActionCommand().equals("Browse users")) {
            view.getBrowseSecondHandProductsP().setVisible(false);
            view.getBrowseWalletOwnersP().setVisible(true);
            try {
                view.getBrowseWalletOwnersP().setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
        updateControllers();
    }
}