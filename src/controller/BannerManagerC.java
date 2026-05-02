package controller;

import java.awt.Window;

import javax.swing.SwingUtilities;

import view.App;
import view.banners.BannerManager;

public class BannerManagerC {

	private BannerManager vista;
	//private Manager user;
	private App frame;

	/**
	 * @param vista
	 */
	public BannerManagerC(BannerManager vista, App frame) {
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
		
	    ManagerPerfil perfil = new ManagerPerfil();
	    
	    new ManagerPerfil();
	    
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
