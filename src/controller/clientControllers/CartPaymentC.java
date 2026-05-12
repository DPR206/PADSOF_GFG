package controller.clientControllers;

import controller.browserControllers.MixedBrowseCartC;
import es.uam.eps.padsof.telecard.*;
import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.clientPanels.CarritoP;
import view.clientPanels.PaymentP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class CartPaymentC {

    private App frame;
    private PaymentP view;
    private RegisteredClient user;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    /**
     * @param frame
     * @param view
     */
    public CartPaymentC(App frame, PaymentP view) {
        this.frame = frame;
        this.view = view;
        this.user = (RegisteredClient) frame.getUser();

        inicializarEventos();
    }

    private void inicializarEventos() {

        // 2. Asignar eventos
        view.getBtnCancelar().addActionListener(e -> view.dispose());

        view.getBtnConfirmar().addActionListener(e -> {
            String tarjeta = view.getNumeroTarjeta();

            try {
                user.getC().payOrder(tarjeta);
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
                CarritoP carritoVista = new CarritoP();
                new CarritoC(carritoVista, frame);
                new MixedBrowseCartC(frame, Store.getInstance(), carritoVista.getCartItems());
                frame.addCard(carritoVista, "CART");
                frame.changeVisibleCard("CART");
                frame.getLastShownPanels().removeLast();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }

        });

        //frame.changeVisibleCard("CART");

    }

}