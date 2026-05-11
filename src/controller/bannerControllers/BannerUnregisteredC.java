package controller.bannerControllers;

import controller.Controller;
import controller.browserControllers.BrowseCartC;
import controller.clientControllers.CarritoC;
import controller.clientControllers.UnregisteredMainC;
import model.store.Store;
import model.user.User;
import view.App;
import view.banners.BannerUnregistered;
import view.clientPanels.CarritoP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class BannerUnregisteredC implements Controller {

    private BannerUnregistered vista;
    private User user;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * @param vista
     */
    public BannerUnregisteredC(BannerUnregistered vista, App frame) {
        this.vista = vista;
        this.user = frame.getUser();
        this.frame = frame;
        initializeActions();
    }

    public void initializeActions() {
        vista.getBtnCarrito().addActionListener(e -> {
            try {
                abrirCarritoDelCliente();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        vista.getHome().addActionListener(e -> {
            try {
                abrirPaginaPrincipal();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        vista.getBtnPerfil().addActionListener(e -> {
            abrirSignUp();
        });

        vista.getBtnExit().addActionListener(e -> {
            abrirWelcome();
        });

        vista.getBtnGoBack().addActionListener(e -> {
            goBack();
        });
    }

    private void goBack() {
        frame.goBack();
    }

    private void abrirWelcome() {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.frame.changeVisibleCard("WELCOME");
        }
    }

    private void abrirSignUp() {

        frame.changeVisibleCard("SIGNUP");
    }

    private void abrirPaginaPrincipal() throws BadLocationException {

        new UnregisteredMainC(frame, Store.getInstance());

        frame.changeVisibleCard("UNREGISTERED_MAIN");
    }

    private void abrirCarritoDelCliente() throws BadLocationException {

        CarritoP carritoVista = new CarritoP();

        new CarritoC(carritoVista, frame);
        new BrowseCartC(frame, Store.getInstance(), carritoVista.getCartItems()); // DUE: Sería mejor usar model

        frame.addCard(carritoVista, "CART");
        frame.changeVisibleCard("CART");

    }

}