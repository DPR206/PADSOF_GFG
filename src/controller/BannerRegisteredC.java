package controller;

import java.awt.Window;

import javax.swing.SwingUtilities;

import model.store.Store;
import model.user.RegisteredClient;
import view.App;
import view.NotificacionP;
import view.SignupP;
import view.banners.BannerRegistered;
import view.RegisteredMainP;

public class BannerRegisteredC {
	
	private BannerRegistered vista;
	private RegisteredClient user;
	private App frame;
	
	/**
	 * @param vista
	 * @param user
	 * @param app
	 */
	public BannerRegisteredC(BannerRegistered vista, RegisteredClient user, App frame) {
		this.vista = vista;
		this.user = user;
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
    }
	
	private void abrirCartera() {
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); 
	    }
		
	    WalletP pagWallet = new WalletP(user);
	    
	    new WalletC(pagWallet, user);
	    
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
		
	    RegisteredProfile profile = new RegisteredProfile();
	    
	    new RegisteredProfileC(profile, user);
	    
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
        new CarritoC(carritoVista, user);
        
        // 3. Mostrar la ventana
        carritoVista.setVisible(true);
        
    }
}
