package controller;

import controller.browserControllers.BrowseValuationProductsC;
import model.product.ConservationStatus;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.employeePanels.MaxiValuateSecondHandP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.util.Objects;

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
        view.getValuate().addActionListener(e -> {
            double valuation = Double.parseDouble(view.getValuation().getText());
            ConservationStatus status = view.getConservationStatusFromName(
                    Objects.requireNonNull(view.getConservationStatus().getSelectedItem()).toString());
            view.getProduct().valuate(valuation, status);
            JOptionPane.showMessageDialog(frame, "Product was valuated", "Product was valuated",
                    JOptionPane.INFORMATION_MESSAGE);
            try {
                BrowseSecondHandProductsP browseSecondHandProductsP = new BrowseSecondHandProductsP("Valuate", null);
                frame.addCard(browseSecondHandProductsP, "BROWSE_VALUATION_PRODUCTS");
                frame.changeVisibleCard("BROWSE_VALUATION_PRODUCTS");
                new BrowseValuationProductsC(frame, browseSecondHandProductsP, model);
                frame.getLastShownPanels().remove(frame.getLastShownPanels().getLast());
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}