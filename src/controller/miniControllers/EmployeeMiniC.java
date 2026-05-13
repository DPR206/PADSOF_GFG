package controller.miniControllers;

import controller.Controller;
import controller.managerControllers.ManagerCrearEmpInd;
import model.user.Employee;
import view.App;
import view.managerPanels.ManagerGestionEmplIndividual;
import view.miniPanels.UserMiniP;

public class EmployeeMiniC implements Controller {

    private UserMiniP employee;
    private ManagerGestionEmplIndividual mge;
    private App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public EmployeeMiniC(App frame, UserMiniP employee) {
        this.employee = employee;
        this.mge = new ManagerGestionEmplIndividual();
        this.frame = frame;
        initializeActions();
    }

    public void initializeActions() {
       this.employee.getButton().addActionListener(e->{
    	   System.out.println("AY");
       		this.frame.addCard(mge, "EMPLEADO INDIVIDUAL");
       		this.frame.changeVisibleCard("EMPLEADO INDIVIDUAL");
       		}
        );  
    }

}