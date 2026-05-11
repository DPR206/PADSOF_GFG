package controller.bannerControllers;

import controller.Controller;
import controller.browserControllers.BrowseMyWalletC;
import controller.clientControllers.CarritoC;
import controller.clientControllers.RegisteredProfileC;
import controller.notifications.NotificacionesC;
import model.store.Store;
import model.user.RegisteredClient;
import model.user.User;
import view.App;
import view.banners.BannerRegistered;
import view.browserPanels.BrowseMyWalletP;
import view.clientPanels.CarritoP;
import view.clientPanels.RegisteredProfile;
import view.notifications.NotificacionP;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class BannerRegisteredC implements Controller {

    private BannerRegistered vista;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     *
     * @param vista
     * @param frame
     */
    public BannerRegisteredC(BannerRegistered vista, App frame) {
        this.vista = vista;
        this.frame = frame;
        initializeActions();
    }

    private void abrirWelcome() {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.frame.updateView("WELCOME", "BANNER_UNREGISTERED");

            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    private void abrirCartera() throws BadLocationException {

//        RegisteredWalletP pagWallet = new RegisteredWalletP((RegisteredClient) frame.getUser());
//
//        new RegisteredWalletC(pagWallet, (RegisteredClient) frame.getUser());

        BrowseMyWalletP pagWallet = new BrowseMyWalletP((RegisteredClient) frame.getUser());
        new BrowseMyWalletC(frame, Store.getInstance(), pagWallet);

        frame.addCard(pagWallet, "WALLET");
        frame.changeVisibleCard("WALLET");

    }

    private void abrirNots() {

        NotificacionP pagNots = new NotificacionP();

        new NotificacionesC(pagNots, frame);

        this.frame.addCard(pagNots, "NOTIFICATIONS");
        this.frame.changeVisibleCard("NOTIFICATIONS");
    }

    private void abrirPerfil() {

        User usuario = frame.getUser();
        RegisteredProfile profile = new RegisteredProfile();

        new RegisteredProfileC(profile, (RegisteredClient) usuario);

        this.frame.addCard(profile, "PROFILE_REGISTERED");
        this.frame.changeVisibleCard("PROFILE_REGISTERED");
    }

    private void abrirPaginaPrincipal() {
        frame.changeVisibleCard("REGISTERED_MAIN");
    }

    private void abrirCarritoDelCliente() {

        CarritoP carritoVista = new CarritoP();

        new CarritoC(carritoVista, frame);

        frame.addCard(carritoVista, "CART");
        frame.changeVisibleCard("CART");

    }

    public void initializeActions() {

        vista.getBtnCarrito().addActionListener(e -> {
            abrirCarritoDelCliente();
        });

        vista.getHome().addActionListener(e -> {
            abrirPaginaPrincipal();
        });

        vista.getBtnPerfil().addActionListener(e -> {
            abrirPerfil();
        });

        vista.getBtnNots().addActionListener(e -> {
            abrirNots();
        });

        vista.getCartera().addActionListener(e -> {
            try {
                abrirCartera();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        vista.getBtnExit().addActionListener(e -> {
            abrirWelcome();
        });
    }
}