package controller.miniControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.managerControllers.ManagerGestionarEmpInd;
import model.user.Employee;
import view.App;
import view.managerPanels.ManagerGestionEmplIndividual;
import view.miniPanels.UserMiniP;

public class EmployeeMiniController implements ActionListener{
	
	private UserMiniP employee;
	private ManagerGestionEmplIndividual mge;
	private App frame;
	
	public EmployeeMiniController(App frame, UserMiniP employee, ManagerGestionEmplIndividual mge) {
		this.employee = employee;
		this.mge = mge;
		this.frame = frame;
		
		mge.setController(new ManagerGestionarEmpInd((Employee)employee.getUser(), mge));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
