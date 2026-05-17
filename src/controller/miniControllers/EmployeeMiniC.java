package controller.miniControllers;

import controller.Controller;
import controller.managerControllers.ManagerGestionarEmpleado;
import view.App;
import view.managerPanels.ManagerGestionEmplIndividual;
import view.miniPanels.UserMiniP;

import javax.swing.text.BadLocationException;

/**
 * The type Employee mini c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class EmployeeMiniC implements Controller {

    private final UserMiniP employee;
    private final ManagerGestionEmplIndividual mge;
    private final App frame;

    /**
     * Instantiates a new Employee mini c.
     * @param frame    the frame
     * @param employee the employee
     */
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public EmployeeMiniC(App frame, UserMiniP employee) {
        this.employee = employee;
        this.mge = new ManagerGestionEmplIndividual();
        this.frame = frame;
        initializeActions();
    }

    public void initializeActions() {
        this.employee.getButton().addActionListener(e -> {
            try {
            	new ManagerGestionarEmpleado(mge, frame, employee);
            	
            	this.frame.addCard(mge, "EMPLEADO INDIVIDUAL");
                this.frame.changeVisibleCard("EMPLEADO INDIVIDUAL");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

}