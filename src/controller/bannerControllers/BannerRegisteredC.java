package controller.bannerControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseCartC;
import controller.browserControllers.MixedBrowseMyWalletC;
import controller.clientControllers.CarritoC;
import controller.clientControllers.RegisteredProfileC;
import controller.notifications.NotificacionesC;
import model.store.Store;
import model.user.*;
import view.App;
import view.banners.BannerRegistered;
import view.browserPanels.BrowseMyWalletP;
import view.clientPanels.CarritoP;
import view.clientPanels.RegisteredProfile;
import view.notifications.NotificacionP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;

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
        new MixedBrowseMyWalletC(frame, Store.getInstance(), pagWallet);

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

    private void abrirCarritoDelCliente() throws BadLocationException {

        CarritoP carritoVista = new CarritoP();
        System.out.println("Check........");
        new CarritoC(carritoVista, frame);
        new MixedBrowseCartC(frame, Store.getInstance(), carritoVista.getCartItems()); // DUE: Sería mejor usar model

        frame.addCard(carritoVista, "CART");
        frame.changeVisibleCard("CART");

    }

    public void initializeActions() {
        vista.getBtnGoBack().setEnabled(!frame.getLastShownPanels().isEmpty());
        vista.revalidate();
        vista.repaint();

        for (ActionListener listener : vista.getBtnCarrito().getActionListeners()) {
            vista.getBtnCarrito().removeActionListener(listener);
        }
        vista.getBtnCarrito().addActionListener(e -> {
            try {
                abrirCarritoDelCliente();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getHome().getActionListeners()) {
            vista.getHome().removeActionListener(listener);
        }
        vista.getHome().addActionListener(e -> {
            abrirPaginaPrincipal();
        });

        for (ActionListener listener : vista.getBtnPerfil().getActionListeners()) {
            vista.getBtnPerfil().removeActionListener(listener);
        }
        vista.getBtnPerfil().addActionListener(e -> {
            abrirPerfil();
        });

        for (ActionListener listener : vista.getBtnNots().getActionListeners()) {
            vista.getBtnNots().removeActionListener(listener);
        }
        vista.getBtnNots().addActionListener(e -> {
            abrirNots();
        });

        for (ActionListener listener : vista.getCartera().getActionListeners()) {
            vista.getCartera().removeActionListener(listener);
        }
        vista.getCartera().addActionListener(e -> {
            try {
                abrirCartera();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnExit().getActionListeners()) {
            vista.getBtnExit().removeActionListener(listener);
        }
        vista.getBtnExit().addActionListener(e -> {
            this.frame.changeCurrentUser(new UnregisteredClient(true));
            abrirWelcome();
        });

        for (ActionListener listener : vista.getBtnGoBack().getActionListeners()) {
            vista.getBtnGoBack().removeActionListener(listener);
        }
        vista.getBtnGoBack().addActionListener(e -> {
            goBack();
        });
    }

    private void goBack() {
        frame.goBack();
    }
}