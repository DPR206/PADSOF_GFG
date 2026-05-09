package controller.bannerControllers;

import controller.*;
import model.user.RegisteredClient;
import model.user.User;
import view.*;
import view.banners.BannerRegistered;

import javax.swing.*;
import java.awt.*;

public class BannerRegisteredC {

    private BannerRegistered vista;
    private User user;
    private App frame;

    /**
     *
     * @param vista
     * @param frame
     */
    public BannerRegisteredC(BannerRegistered vista, App frame) {
        this.vista = vista;
        this.user = frame.getUser();
        this.frame = frame;
        inicializarEventos();
    }

    private void inicializarEventos() {

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

    private void abrirWelcome() {

        int respuesta =
                JOptionPane.showConfirmDialog(this.frame, "Are you sure you want to log out?", "Confirm log out",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            this.vista.setVisible(false);
            this.frame.getWelcomePanel().setVisible(true);

            this.frame.revalidate();
            this.frame.repaint();
        }
    }

    private void abrirCartera() {
        Window ventanaActual = SwingUtilities.getWindowAncestor(vista);

        if (ventanaActual != null) {
            ventanaActual.dispose();
        }

        RegisteredWalletP pagWallet = new RegisteredWalletP((RegisteredClient) user);

        new RegisteredWalletC(pagWallet, (RegisteredClient) user);

        pagWallet.setVisible(true);

    }

    private void abrirNots() {
        Window ventanaActual = SwingUtilities.getWindowAncestor(vista);

        if (ventanaActual != null) {
            ventanaActual.dispose();
        }

        //NotificacionP pagNots = new NotificacionP(new BannerRegistered());
        NotificacionP pagNots = new NotificacionP(vista);

        new NotificacionesC(pagNots, frame);

        pagNots.setVisible(true);
    }

    private void abrirPerfil() {
        Window ventanaActual = SwingUtilities.getWindowAncestor(vista);

        if (ventanaActual != null) {
            ventanaActual.dispose();
        }

        RegisteredProfile profile = new RegisteredProfile(vista);

        new RegisteredProfileC(profile, (RegisteredClient) user);

        profile.setVisible(true);
    }

    private void abrirPaginaPrincipal() {

        Window ventanaActual = SwingUtilities.getWindowAncestor(vista);

        if (ventanaActual != null) {
            ventanaActual.dispose(); // Cerramos la ventana
        }

        //RegisteredMainP pagPrin = new RegisteredMainP();

        //new RegisteredMainC(frame, Store.getInstance());

        frame.getRegisteredMainPanel().setVisible(true);
    }

    private void abrirCarritoDelCliente() {

        Window ventanaActual = SwingUtilities.getWindowAncestor(vista);

        if (ventanaActual != null) {
            ventanaActual.dispose(); // Cerramos la ventana
        }

        // 1. Crear la vista del carrito
        CarritoP carritoVista = new CarritoP();

        // 2. Crear el controlador del carrito pasando el usuario actual
        new CarritoC(carritoVista, (RegisteredClient) user);

        // 3. Mostrar la ventana
        carritoVista.setVisible(true);

    }
}