package controller.miniControllers;

import controller.Controller;
import controller.managerControllers.ManagerGestionarEmpInd;
import model.user.Employee;
import view.App;
import view.managerPanels.ManagerGestionEmplIndividual;
import view.miniPanels.UserMiniP;

public class EmployeeMiniC implements Controller {

    private UserMiniP employee;
    private ManagerGestionEmplIndividual mge;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public EmployeeMiniC(App frame, UserMiniP employee, ManagerGestionEmplIndividual mge) {
        this.employee = employee;
        this.mge = mge;
        this.frame = frame;

        new ManagerGestionarEmpInd((Employee) employee.getUser(), mge);
        initializeActions();
    }

    public void initializeActions() {
        // TODO Auto-generated method stub
    }

}