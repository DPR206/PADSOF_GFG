package controller.clientControllers;

import controller.browserControllers.AbstractBrowserC;
import es.uam.eps.padsof.telecard.*;
import model.product.SecondHandProduct;
import model.user.RegisteredClient;
import view.App;
import view.browserPanels.AbstractBrowserP;
import view.clientPanels.PaymentP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class ValuationPaymentC {
    private final SecondHandProduct product;
    private final AbstractBrowserC<SecondHandProduct> abstractBrowserC;
    private final AbstractBrowserP<SecondHandProduct> abstractBrowserP;
    private App frame;
    private PaymentP view;
    private RegisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * @param frame
     * @param view
     */
    public ValuationPaymentC(App frame, PaymentP view, AbstractBrowserC<SecondHandProduct> abstractBrowserC,
                             AbstractBrowserP<SecondHandProduct> abstractBrowserP, SecondHandProduct product) {
        this.frame = frame;
        this.view = view;
        this.user = (RegisteredClient) frame.getUser();
        this.abstractBrowserC = abstractBrowserC;
        this.abstractBrowserP = abstractBrowserP;
        this.product = product;

        inicializarEventos();
    }

    private void inicializarEventos() {

        view.getBtnCancelar().addActionListener(e -> view.dispose());

        view.getBtnConfirmar().addActionListener(e -> {
            String tarjeta = view.getNumeroTarjeta();

            try {
                product.payValuation(tarjeta);
                JOptionPane.showMessageDialog(view, "Payment successful!");
                view.dispose();
                user.getC().emptyCart();
            } catch (InvalidCardNumberException e1) {
                JOptionPane.showMessageDialog(view, "Invalid card number", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (FailedInternetConnectionException e1) {
                JOptionPane.showMessageDialog(view, "Failed Internet connection", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (OrderRejectedException e1) {
                JOptionPane.showMessageDialog(view, "Order rejected", "Error", JOptionPane.ERROR_MESSAGE);
            }

            try {
                abstractBrowserP.paintEverything();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
            abstractBrowserC.initializeActionsForMiniPanels();

        });
    }
}