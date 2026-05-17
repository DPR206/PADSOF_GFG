package controller.employeeControllers;

import controller.Controller;
import model.user.Employee;
import view.App;
import view.employeePanels.EmployeeProfile;

import javax.swing.*;

/**
 * The type Employee profile c.
 * @author Duna P.R.
 * @version 1.0
 */
public class EmployeeProfileC implements Controller {

    private final EmployeeProfile vista;
    private final Employee user;
    private boolean passwordRevelada = false;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Employee profile c.
     * @param vista the vista
     * @param frame the frame
     */
    public EmployeeProfileC(EmployeeProfile vista, App frame) {
        this.vista = vista;
        this.user = (Employee) frame.getUser();
        initializeActions();
    }

    @Override
    public void initializeActions() {

        vista.setNom(user.getUserName());

        vista.getBtnMostrar().addActionListener(e -> showPassword());

        configurarSeccionPermisos();
    }

    /**
     * It shows or hides the password
     */
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

    /**
     * It configures the permission's section
     */
    private void configurarSeccionPermisos() {

        hacerInmutable(vista.getExchanges(), user.getEp() != null);
        hacerInmutable(vista.getStore(), user.getSp() != null);
        hacerInmutable(vista.getOrders(), user.getOp() != null);
    }

    /**
     * It makes a checkbox inmutable
     * @param check the desired checkbox
     * @param valor the checkbox's value
     */
    private void hacerInmutable(JCheckBox check, boolean valor) {
        check.setModel(new DefaultButtonModel() {
            private boolean inicializado = false;

            @Override
            public void setArmed(boolean b) { /* Ignorar preparación */ }

            @Override
            public void setPressed(boolean b) { /* Ignorar clic visual */ }

            @Override
            public void setSelected(boolean b) {
                if (!inicializado) {
                    super.setSelected(b);
                    inicializado = true;
                }
            }
        });

        check.setSelected(valor);
        check.setFocusable(false);
    }

}