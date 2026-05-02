package controller;

import java.awt.Window;

import javax.swing.SwingUtilities;

import model.user.Employee;
import view.App;
import view.NotificacionP;
import view.banners.BannerEmployee;

public class BannerEmployeeC {

	private BannerEmployee vista;
	private Employee user;
	private App frame;
	
	/**
	 * @param vista
	 * @param user
	 * @param app
	 */
	public BannerEmployeeC(BannerEmployee vista, Employee user, App frame) {
		this.vista = vista;
		this.user = user;
		this.frame = frame;
		filtrarBotones();
		inicializarEventos();
	}

	private void filtrarBotones() {
		
		if(user.getSp() != null)
			vista.getTienda().setVisible(true);
		else
			vista.getTienda().setVisible(false);
		
		if(user.getOp() != null)
			vista.getBtnCarrito().setVisible(true);
		else
			vista.getBtnCarrito().setVisible(false);
		
		if(user.getEp() != null)
			vista.getIntercambios().setVisible(true);
		else
			vista.getIntercambios().setVisible(false);
		
	}

	private void inicializarEventos() {

        vista.getBtnCarrito().addActionListener(e -> {
            abrirPedidos();
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
        
        vista.getTienda().addActionListener(e -> {
        	abrirTienda();
        });
        
        vista.getIntercambios().addActionListener(e -> {
        	abrirIntercambios();
        });
    }

	private void abrirIntercambios() {
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); 
	    }
	    
	    EmloyeeExchange pagExchange = new EmployeeExchange();
	    new EmployeeExchangeC();
	    
	    pagExchange.setVisible(true);
		
	}

	private void abrirPedidos() {
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); 
	    }
	    
	    EmloyeeOrder pagOrder = new EmployeeOrder();
	    new EmployeeOrderC();
	    
	    pagOrder.setVisible(true);
		
	}

	private void abrirTienda() {
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); 
	    }
		
	    EmloyeeTienda pagTienda = new EmployeeTienda();
	    new EmployeeTiendaC();
	    
	    pagTienda.setVisible(true);
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
		
	    EmployeeProfile profile = new EmployeeProfile();
	    
	    new EmployeeProfileC(profile, user);
	    
	    profile.setVisible(true);
	}


	private void abrirPaginaPrincipal() {
		
		Window ventanaActual = SwingUtilities.getWindowAncestor(vista);
	    
	    if (ventanaActual != null) {
	        ventanaActual.dispose(); // Cerramos la ventana 
	    }
	    
	    frame.getEmployeeMainPanel().setVisible(true);
	}

}
