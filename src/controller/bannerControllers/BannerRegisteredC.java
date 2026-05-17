package controller.bannerControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseCartC;
import controller.clientControllers.*;
import controller.notifications.NotificacionesC;
import model.store.Store;
import model.user.RegisteredClient;
import model.user.UnregisteredClient;
import view.App;
import view.banners.BannerRegistered;
import view.clientPanels.*;
import view.notifications.NotificacionP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;

/**
 * It implements the registered user's banner
 * @author Duna P.R.
 * @version 1.0
 */
public class BannerRegisteredC implements Controller {

    private final BannerRegistered vista;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Banner registered c.
     * @param vista the vista
     * @param frame the frame
     */
    public BannerRegisteredC(BannerRegistered vista, App frame, Store model) throws BadLocationException {
        this.vista = vista;
        this.frame = frame;
        this.model = model;

        NotificacionP pagNots = new NotificacionP();
        new NotificacionesC(pagNots, frame);
        this.frame.addCard(pagNots, "NOTIFICATIONS");

        CarritoP carritoVista = new CarritoP();
        new CarritoC(carritoVista, frame);
        new MixedBrowseCartC(frame, model, carritoVista.getCartItems());
        frame.addCard(carritoVista, "CART");

        initializeActions();
    }

    /**
     * It opens the welcome page
     */
    private void abrirWelcome() throws BadLocationException {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.frame.updateView("WELCOME", "BANNER_UNREGISTERED");

            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    /**
     * It opens the wallet page
     * @throws BadLocationException the bad location exception
     */
    private void abrirCartera() throws BadLocationException {
        RegisteredWalletP pagWallet = new RegisteredWalletP((RegisteredClient) frame.getUser());
        new RegisteredWalletC(frame, model, pagWallet);
        frame.addCard(pagWallet, "WALLET");
        frame.changeVisibleCard("WALLET");
    }

    /**
     * It opens the notifications page
     */
    private void abrirNots() throws BadLocationException {
        this.frame.changeVisibleCard("NOTIFICATIONS");
    }

    /**
     * It opens the profile page
     */
    private void abrirPerfil() throws BadLocationException {
        RegisteredProfile profile = new RegisteredProfile();
        new RegisteredProfileC(profile, (RegisteredClient) frame.getUser());
        this.frame.addCard(profile, "PROFILE_REGISTERED");
        this.frame.changeVisibleCard("PROFILE_REGISTERED");
    }

    /**
     * It opens the main page
     */
    private void abrirPaginaPrincipal() throws BadLocationException {
        frame.changeVisibleCard("REGISTERED_MAIN");
    }

    /**
     * It opens the cart
     * @throws BadLocationException the bad location exception
     */
    private void abrirCarritoDelCliente() throws BadLocationException {
        frame.changeVisibleCard("CART");

    }

    @Override
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
            try {
                abrirPaginaPrincipal();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnPerfil().getActionListeners()) {
            vista.getBtnPerfil().removeActionListener(listener);
        }
        vista.getBtnPerfil().addActionListener(e -> {
            try {
                abrirPerfil();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnNots().getActionListeners()) {
            vista.getBtnNots().removeActionListener(listener);
        }
        vista.getBtnNots().addActionListener(e -> {
            try {
                abrirNots();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
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
            try {
                abrirWelcome();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnGoBack().getActionListeners()) {
            vista.getBtnGoBack().removeActionListener(listener);
        }
        vista.getBtnGoBack().addActionListener(e -> {
            try {
                frame.goBack();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}