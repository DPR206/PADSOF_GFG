package controller;

import model.user.Employee;
import view.EmployeeProfile;

public class EmployeeProfileC {

	private EmployeeProfile vista;
	private Employee user;
	private boolean passwordRevelada = false;
	
	/**
	 * @param vista
	 * @param user
	 */
	public EmployeeProfileC(EmployeeProfile vista, Employee user) {
		this.vista = vista;
		this.user = user;
		inicializarEventos();
	}

	private void inicializarEventos() {
		
		vista.setName(user.getUserName());
		
		vista.getBtnMostrar().addActionListener(e -> {
			showPassword();
		});
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
