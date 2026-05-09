package controller.bannerControllers;

import controller.clientControllers.UnregisteredMainC;
import model.store.Store;
import model.user.UnregisteredClient;
import model.user.User;
import view.App;
import view.banners.BannerUnregistered;
import view.clientPanels.CarritoP;
import view.clientPanels.UnregisteredMainP;

import javax.swing.*;

public class BannerUnregisteredC {

    private BannerUnregistered vista;
    private User user;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * @param vista
     */
    public BannerUnregisteredC(BannerUnregistered vista, /*UnregisteredClient user,*/ App frame) {
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

        //SignupP signUp = frame.getSignupPanel();

        //new SignupC(frame, Store.getInstance());

        frame.changeVisibleCard("SIGNUP");

		/*this.frame.getContentPane().remove(this.vista); // Quita el panel actual
	    this.frame.getContentPane().add(this.frame.getSignupPanel()); // Añade el nuevo

	    this.frame.getContentPane().revalidate();
	    this.frame.getContentPane().repaint();*/
    }

    private void abrirPaginaPrincipal() {

        UnregisteredMainP pagPrin = frame.getUnregisteredMainPanel();

        new UnregisteredMainC(frame, Store.getInstance(), (UnregisteredClient) frame.getUser());
        new UnregisteredMainC(frame, Store.getInstance(), (UnregisteredClient) frame.getUser());

        frame.changeVisibleCard("UNREGISTERED_MAIN");
    }

    private void abrirCarritoDelCliente() {

        // 1. Crear la vista del carrito
        CarritoP carritoVista = new CarritoP();

        // 2. Crear el controlador del carrito pasando el usuario actual
        //new CarritoC(carritoVista, user);

        // 3. Mostrar la ventana
        carritoVista.setVisible(true);

    }

}