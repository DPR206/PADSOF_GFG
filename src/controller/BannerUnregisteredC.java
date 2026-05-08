package controller;

import view.App;
import view.CarritoP;
import view.banners.BannerUnregistered;

public class BannerUnregisteredC {

    private BannerUnregistered vista;
    //private UnregisteredClient user;
    private App frame;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    /**
     * @param vista
     */
    public BannerUnregisteredC(BannerUnregistered vista, /*UnregisteredClient user,*/ App frame) {
        this.vista = vista;
        //this.user = frame.getUser();
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
    }

    private void abrirSignUp() {
		/*Window ventanaActual = SwingUtilities.getWindowAncestor(vista);

	    if (ventanaActual != null) {
	        ventanaActual.dispose(); // Cerramos la ventana
	    }*/

        //SignupP signUp = frame.getSignupPanel();

        //new SignupC(frame, Store.getInstance());

        frame.changeVisibleCard("SIGNUP");

    }

    private void abrirPaginaPrincipal() {

        //UnregisteredMainP pagPrin = frame.getUnregisteredMainPanel();

        //new UnregisteredMainC(pagPrin, user);
        //new UnregisteredMainC(frame, Store.getInstance());

        frame.getUnregisteredMainPanel().setVisible(true);
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