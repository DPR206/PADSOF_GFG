package controller.bannerControllers;

import controller.Controller;
import controller.clientControllers.*;
import controller.notifications.NotificacionesC;
import model.user.RegisteredClient;
import model.user.User;
import view.App;
import view.banners.BannerRegistered;
import view.clientPanels.*;
import view.notifications.NotificacionP;

import javax.swing.*;

public class BannerRegisteredC implements Controller {

    private BannerRegistered vista;
    private User user;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     *
     * @param vista
     * @param frame
     */
    public BannerRegisteredC(BannerRegistered vista, App frame) {
        this.vista = vista;
        this.user = frame.getUser();
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

    private void abrirCartera() {

        RegisteredWalletP pagWallet = new RegisteredWalletP((RegisteredClient) user);

        new RegisteredWalletC(pagWallet, (RegisteredClient) user);

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
            abrirCartera();
        });

        vista.getBtnExit().addActionListener(e -> {
            abrirWelcome();
        });
    }
}