package controller.managerControllers;

import controller.Controller;
import model.user.Employee;
import model.user.Permission;
import model.utilities.exceptions.PasswordNotValid;
import view.App;
import view.managerPanels.ManagerGestionEmplIndividual;
import view.miniPanels.UserMiniP;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Manager gestionar empleado.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerGestionarEmpleado implements Controller {

    private final UserMiniP employee;
    private final App frame;
    private final ManagerGestionEmplIndividual mge;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager gestionar empleado.
     * @param mng   the mng
     * @param frame the frame
     * @param emp   the emp
     */
    public ManagerGestionarEmpleado(ManagerGestionEmplIndividual mng, App frame, UserMiniP emp) {
        this.mge = mng;
        this.frame = frame;
        this.employee = emp;
    }

    @Override
    public void initializeActions() {
        this.mge.getConfirmar().addActionListener(e -> {
            String userName = this.mge.getUserName().getText();
            if (userName == null) {
                return;
            }
            String pwd = this.mge.getPwd().getText();
            if (pwd == null) {
                return;
            }

            List<Permission> perms = new ArrayList<>();

            if (mge.getStorePerm().isSelected()) {
                perms.add(Permission.STORE);
            }
            if (mge.getExchangePerm().isSelected()) {
                perms.add(Permission.EXCHANGE);
            }
            if (mge.getOrderPerm().isSelected()) {
                perms.add(Permission.ORDER);
            }

            if (perms.isEmpty()) {
                return;
            }

            Permission[] p = perms.toArray(new Permission[0]);

            Employee emp = (Employee) this.employee.getUser();
            emp.setPerm(p);

            emp.setUserName(userName);
            try {
                emp.changePassword(pwd);
            } catch (PasswordNotValid e1) {
                throw new RuntimeException(e1);
            }
            try {
                this.frame.changeVisibleCard("GESTIONAR_EMPL");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}