package controller;

import java.awt.Window;

import javax.swing.SwingUtilities;

import model.user.RegisteredClient;
import model.user.UnregisteredClient;
import model.user.User;
import view.App;
import view.BannerUnregistered;
import view.SignupP;
import view.UnregisteredMainP;

public class BannerUnregisteredC {
	
	private BannerUnregistered vista;
	private UnregisteredClient user;
	private App app;

	/**
	 * @param vista
	 */
	public BannerUnregisteredC(BannerUnregistered vista, UnregisteredClient user, App app) {
		this.vista = vista;
		this.user = user;
		this.app = app;
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
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); // Cerramos la ventana de notificaciones (o donde esté)
	    }
		
	    SignupP signUp = new SignupP();
	    
	    new SignupC(app, store);
	    
	    signUp.setVisible(true);
	}


	private void abrirPaginaPrincipal() {
		
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); // Cerramos la ventana de notificaciones (o donde esté)
	    }
	    
	    UnregisteredMainP pagPrin = new UnregisteredMainP(user, app);
	    
	    new UnregisteredClientC(pagPrin, user);
	    
	    pagPrin.setVisible(true);
	}


	private void abrirCarritoDelCliente() {
		
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); // Cerramos la ventana de notificaciones (o donde esté)
	    }
        
        // 1. Crear la vista del carrito
        CarritoP carritoVista = new CarritoP(); 
        
        // 2. Crear el controlador del carrito pasando el usuario actual
        new CarritoC(carritoVista, (RegisteredClient) user);
        
        // 3. Mostrar la ventana
        carritoVista.setVisible(true);
        
    }

}
