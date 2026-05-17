package controller.bannerControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseCartC;
import controller.clientControllers.CarritoC;
import model.store.Store;
import view.App;
import view.banners.BannerUnregistered;
import view.clientPanels.CarritoP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;

/**
 * It implements the unregistered client's banner
 * @author Duna P.R.
 * @version 1.0
 */
public class BannerUnregisteredC implements Controller {

    private final BannerUnregistered vista;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Banner unregistered c.
     * @param vista the vista
     * @param frame the frame
     */
    public BannerUnregisteredC(BannerUnregistered vista, App frame, Store model) {
        this.vista = vista;
        this.frame = frame;
        this.model = model;
        initializeActions();
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
                abrirSignUp();
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        for (ActionListener listener : vista.getBtnExit().getActionListeners()) {
            vista.getBtnExit().removeActionListener(listener);
        }
        vista.getBtnExit().addActionListener(e -> {
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

    /**
     * It opens the welcome screen
     */
    private void abrirWelcome() throws BadLocationException {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.frame.changeVisibleCard("WELCOME");
        }
    }

    /**
     * It opens the signup panel
     */
    private void abrirSignUp() throws BadLocationException {

        frame.changeVisibleCard("SIGNUP");
    }

    /**
     * It opens the main panel
     * @throws BadLocationException the bad location exception
     */
    private void abrirPaginaPrincipal() throws BadLocationException {
        frame.changeVisibleCard("UNREGISTERED_MAIN");
    }

    /**
     * It opens the cart
     * @throws BadLocationException the bad location exception
     */
    private void abrirCarritoDelCliente() throws BadLocationException {

        CarritoP carritoVista = new CarritoP();

        new CarritoC(carritoVista, frame);
        new MixedBrowseCartC(frame, model, carritoVista.getCartItems());

        frame.addCard(carritoVista, "CART");
        frame.changeVisibleCard("CART");

    }

}