package controller.managerControllers;

import java.awt.Window;

import javax.swing.SwingUtilities;

import model.user.Manager;
import view.App;
import view.managerPanels.GestorChangePwd;
import view.managerPanels.ManagerProfile;

public class ManagerProfileC {

	private ManagerProfile vista;
	private Manager user;
	private App frame;
	private boolean passwordRevelada = false;

	/**
	 * @param vista
	 * @param user
	 */
	public ManagerProfileC(ManagerProfile vista, Manager user, App frame) {
		this.vista = vista;
		this.user = user;
		this.frame = frame;
		inicializarEventos();
	}

	private void inicializarEventos() {

		vista.setNom(user.getUserName());

		vista.getBtnMostrar().addActionListener(e -> {
			showPassword();
		});

		vista.getBtnCambiar().addActionListener(e -> {
			cambiarPwd();
		});
	}

	private void cambiarPwd() {

	    GestorChangePwd pagPwd = new GestorChangePwd();

	    new GestorChangePwdC(pagPwd, user);

	    frame.addCard(pagPwd, "PROFILE_MANAGER");
	    frame.changeVisibleCard("PROFILE_MANAGER");
	}

	private void showPassword() {
		if (passwordRevelada) {
		    vista.setPwd("********");
		    vista.getBtnMostrar().setText("👁️");
		} else {
		    vista.setPwd(user.getPassword());
		    vista.getBtnMostrar().setText("🔒");
		}
		passwordRevelada = !passwordRevelada;
	}
}