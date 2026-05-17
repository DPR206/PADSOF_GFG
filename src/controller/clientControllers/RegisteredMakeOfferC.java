package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.BrowseSomeonesWalletC;
import model.product.SecondHandProduct;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.RegisteredMakeOfferP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.ArrayList;

/**
 * The type Registered make offer c.
 * @author Ana O.R.
 * @version 1.0
 */
public class RegisteredMakeOfferC implements Controller {
    private final App frame;
    private final Store model;
    private final RegisteredMakeOfferP view;
    private final RegisteredClient them;
    private final ArrayList<SecondHandProduct> theirProducts = new ArrayList<>();
    private final ArrayList<SecondHandProduct> myProducts = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered make offer c.
     * @param frame the frame
     * @param model the model
     * @param view  the view
     * @param them  the them
     * @throws BadLocationException the bad location exception
     */
    public RegisteredMakeOfferC(App frame, Store model, RegisteredMakeOfferP view, RegisteredClient them)
            throws BadLocationException {
        this.frame = frame;
        this.model = model;
        this.view = view;
        this.them = them;

        new BrowseSomeonesWalletC(frame, model, view.getBrowseTheirWallet());
        new BrowseSomeonesWalletC(frame, model, view.getBrowseMyWallet());
    }

    /**
     * Add product from their wallet.
     * @param product the product
     */
    public void addProductFromTheirWallet(SecondHandProduct product) {
        theirProducts.add(product);
    }

    /**
     * Add product from my wallet.
     * @param product the product
     */
    public void addProductFromMyWallet(SecondHandProduct product) {
        myProducts.add(product);
    }

    @Override
    public void initializeActions() throws BadLocationException {
        view.getMakeOfferButton().addActionListener(e -> {
            if (theirProducts.isEmpty() || myProducts.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "There must be at least one product from each wallet",
                        "Minimum products not reached", JOptionPane.ERROR_MESSAGE);
            } else {
                ((RegisteredClient) frame.getUser()).makeAnOffer(theirProducts, myProducts);
                JOptionPane.showMessageDialog(frame, "Offer made successfully");
                try {
                    frame.goBack();
                } catch (BadLocationException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}