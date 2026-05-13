package controller.managerControllers;

import controller.Controller;
import model.user.*;
import model.utilities.exceptions.PasswordNotValid;
import view.managerPanels.ManagerGestionEmplIndividual;

public class ManagerCrearEmpInd implements Controller {
    private Employee emp;
    private ManagerGestionEmplIndividual mgei;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
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
                // TODO Auto-generated catch block
                e1.printStackTrace();
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