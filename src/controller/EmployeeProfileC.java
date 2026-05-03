package controller;

import javax.swing.*;

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
		
		configurarSeccionPermisos();
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
	
	private void configurarSeccionPermisos() {
        hacerInmutable(vista.getExchanges(), user.getEp() != null);
        hacerInmutable(vista.getStore(), user.getSp() != null);
        hacerInmutable(vista.getOrders(), user.getOp() != null);
    }
	
	private void hacerInmutable(JCheckBox check, boolean valor) {
	    check.setSelected(valor);
	    check.setFocusable(false);
	    check.setModel(new DefaultButtonModel() {
	        @Override
	        public void setSelected(boolean b) {
	            // No hacemos nada, ignoramos el intento de cambio
	        }
	        @Override
	        public void setPressed(boolean b) {
	            // No permitimos que se vea el efecto de "presionado"
	        }
	        @Override
            public void setArmed(boolean b) {
                // Evita que el componente se prepare para un evento
            }
	    });
	}
	
}
