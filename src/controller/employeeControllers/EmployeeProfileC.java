package controller.employeeControllers;

import controller.Controller;
import model.user.Employee;
import view.App;
import view.employeePanels.EmployeeProfile;

import javax.swing.*;

public class EmployeeProfileC implements Controller {

    private EmployeeProfile vista;
    private Employee user;
    private boolean passwordRevelada = false;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * @param vista
     * @param user
     */
    public EmployeeProfileC(EmployeeProfile vista, App frame) {
        this.vista = vista;
        this.user = (Employee) frame.getUser();
        initializeActions();
    }

    public void initializeActions() {

        vista.setNom(user.getUserName());

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
    	check.setModel(new DefaultButtonModel() {
            private boolean inicializado = false;

            @Override
            public void setSelected(boolean b) {
                if (!inicializado) {
                    super.setSelected(b);
                    inicializado = true;
                }
            }
            
            @Override
            public void setPressed(boolean b) { /* Ignorar clic visual */ }
            @Override
            public void setArmed(boolean b) { /* Ignorar preparación */ }
        });

        check.setSelected(valor);
        check.setFocusable(false);
    }

}