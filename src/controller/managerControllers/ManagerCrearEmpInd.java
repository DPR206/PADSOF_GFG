package controller.managerControllers;

import controller.Controller;
import model.user.*;
import model.utilities.exceptions.PasswordNotValid;
import view.managerPanels.ManagerGestionEmplIndividual;

/**
 * The type Manager crear emp ind.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerCrearEmpInd implements Controller {
    private final Employee emp;
    private final ManagerGestionEmplIndividual mgei;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager crear emp ind.
     * @param emp  the emp
     * @param mgei the mgei
     */
    public ManagerCrearEmpInd(Employee emp, ManagerGestionEmplIndividual mgei) {
        this.mgei = mgei;
        this.emp = emp;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        mgei.getConfirmar().addActionListener(e -> {
            String userName = mgei.getUserName().getText();
            if (userName == null) {
                return;
            }
            String pwd = mgei.getPwd().getText();
            if (pwd == null) {
                return;
            }

            emp.setUserName(userName);
            try {
                emp.changePassword(pwd);
            } catch (PasswordNotValid e1) {
                throw new RuntimeException(e1);
            }
            /*Ahora revisamos los permisos*/
            if (mgei.getStorePerm().isSelected()) {
                emp.setSp(new StorePermission());
            }
            if (mgei.getOrderPerm().isSelected()) {
                emp.setOp(new OrderPermission(false));
            }
            if (mgei.getExchangePerm().isSelected()) {
                emp.setEp(new ExchangePermission());
            }
        });
    }
}