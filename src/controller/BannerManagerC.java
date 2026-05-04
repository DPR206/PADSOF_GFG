package controller;

import java.awt.Window;

import javax.swing.SwingUtilities;

import model.store.Store;
import view.App;
import view.ManagerProfile;
import view.banners.BannerManager;

public class BannerManagerC {

	private BannerManager vista;
	//private Manager user;
	private App frame;
	private Store store;

	/**
	 * @param vista
	 */
	public BannerManagerC(BannerManager vista, App frame, Store store) {
		this.vista = vista;
		//this.user = frame.getUser();
		this.frame = frame;
        inicializarEventos();
	}
	
	
	private void inicializarEventos() {
        
        vista.getHome().addActionListener(e -> {
        	abrirPaginaPrincipal();
        });
        
        vista.getBtnPerfil().addActionListener(e -> {
        	abrirPerfil();
        });
    }
	
	private void abrirPerfil() {
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); // Cerramos la ventana
	    }
		
	    ManagerProfile perfil = new ManagerProfile(vista);
	    
	    new ManagerProfileC(perfil, store.getManager());
	    
	    perfil.setVisible(true);
	}


	private void abrirPaginaPrincipal() {
		
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); // Cerramos la ventana
	    }
	    
	    frame.getManagerMainPanel().setVisible(true);;
	}

}
