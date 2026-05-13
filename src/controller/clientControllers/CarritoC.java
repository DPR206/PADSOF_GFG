package controller.clientControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseCartC;
import model.store.Store;
import model.user.*;
import view.App;
import view.clientPanels.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class CarritoC implements Controller {
    private App frame;
    private CarritoP view;
    private User user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public CarritoC(CarritoP carritoVista, App frame) {
        this.frame = frame;
        this.view = carritoVista;
        this.user = frame.getUser();

        configurarVisibilidad();
        initializeActions();
    }

    private void configurarVisibilidad() {
        boolean esRegistrado = (user instanceof RegisteredClient);
        view.getBtnOrders().setVisible(esRegistrado);
    }

    public void initializeActions() {

        if (user instanceof RegisteredClient c) {
            view.setTotal(c.getC().calculatePrice());
        } else if (user instanceof UnregisteredClient c) {
            view.setTotal(c.getCart().calculatePrice());
        }

        view.getBtnOrders().addActionListener(e -> {
            abrirOrders();
        });

        view.getBtnPay().addActionListener(e -> {
            makePayment();
        });

        view.getBtnDeleteAll().addActionListener(e -> {
            deleteAll();
        });
    }

    private void deleteAll() {

        int respuesta = JOptionPane.showConfirmDialog(view, "Are you sure you want to empty the cart?", "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (user instanceof RegisteredClient c) {
            if (respuesta == JOptionPane.YES_OPTION) {
                c.getC().emptyCart();
                updateInterface();
            }
        } else if (user instanceof UnregisteredClient c) {
            if (respuesta == JOptionPane.YES_OPTION) {
                c.getCart().emptyCart();
                updateInterface();
            }
        }
    }

    private void updateInterface() {
        try {
            System.out.println("Updating cart..");
            CarritoP carritoVista = new CarritoP();
            new CarritoC(carritoVista, frame);
            new MixedBrowseCartC(frame, Store.getInstance(), carritoVista.getCartItems());
            frame.addCard(carritoVista, "CART");
            frame.changeVisibleCard("CART");
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void makePayment() {

        if (user instanceof RegisteredClient c) {
            double totalActual = c.getC().calculatePrice();

            if (totalActual <= 0) {
                JOptionPane.showMessageDialog(view, "Your cart is empty");
                return;
            }

            PaymentP ventana = new PaymentP(frame, totalActual);
            new CartPaymentC(frame, ventana);

            ventana.setVisible(true);
        } else if (user instanceof UnregisteredClient) {
            JOptionPane.showMessageDialog(view, "You must be logged in to make a payment.");
            frame.changeVisibleCard("SIGNUP");
        }
    }

    private void abrirOrders() {

        if (user instanceof RegisteredClient) {
            OrdersP pagOrders;
			try {
				pagOrders = new OrdersP();
			} catch (BadLocationException ex) {
				throw new RuntimeException(ex);
			}
            new OrdersC(pagOrders, frame);

            frame.addCard(pagOrders, "ORDERS_REGISTERED");
            frame.changeVisibleCard("ORDERS_REGISTERED");
        }
    }

}