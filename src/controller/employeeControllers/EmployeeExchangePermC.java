package controller.employeeControllers;

import controller.Controller;
import controller.browserControllers.BrowseExchangesC;
import controller.browserControllers.BrowseValuationProductsC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseExchangesP;
import view.browserPanels.BrowseSecondHandProductsP;
import view.employeePanels.EmployeeExchangePermP;

import javax.swing.text.BadLocationException;

public class EmployeeExchangePermC implements Controller {
    private final EmployeeExchangePermP view;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public EmployeeExchangePermC(App frame, Store model, EmployeeExchangePermP view) throws BadLocationException {
        this.frame = frame;
        this.view = view;

        BrowseExchangesP exchangeView = new BrowseExchangesP();
        this.frame.addCard(exchangeView, "MANAGE_EXCHANGES");
        new BrowseExchangesC(this.frame, exchangeView, model);

        try {
            BrowseSecondHandProductsP valuateView = new BrowseSecondHandProductsP("Valuate", null);
            this.frame.addCard(valuateView, "BROWSE_VALUATION_PRODUCTS");
            new BrowseValuationProductsC(this.frame, valuateView, model);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getBrowseExchanges().addActionListener(e -> {
            try {
                frame.changeVisibleCard("MANAGE_EXCHANGES");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getValuateProducts().addActionListener(e -> {
            try {
                frame.changeVisibleCard("BROWSE_VALUATION_PRODUCTS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}