package controller.bannerControllers;

import javax.swing.JOptionPane;
import controller.managerControllers.ManagerProfileC;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerProfile;
import view.banners.BannerManager;

public class BannerManagerC {

	private BannerManager vista;
	private App frame;
	private Store store;

	/**
	 * @param vista
	 */
	public BannerManagerC(BannerManager vista, App frame) {
		this.vista = vista;
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

        vista.getBtnExit().addActionListener(e -> {
        	abrirWelcome();
        });
    }

	private void abrirWelcome() {

		int respuesta = JOptionPane.showConfirmDialog(
		        this.frame,
		        "Are you sure you want to log out?",
		        "Confirm log out",
		        JOptionPane.YES_NO_OPTION,
		        JOptionPane.QUESTION_MESSAGE
		);

		if (respuesta == JOptionPane.YES_OPTION) {
	        this.vista.setVisible(false);
	        this.frame.getWelcomePanel().setVisible(true);

	        this.frame.revalidate();
	        this.frame.repaint();
	    }
	}

	private void abrirPerfil() {

	    ManagerProfile perfil = new ManagerProfile();

	    new ManagerProfileC(perfil, store.getManager());

	    perfil.setVisible(true);
	}


	private void abrirPaginaPrincipal() {

	    frame.getManagerMainPanel().setVisible(true);;
	}

}