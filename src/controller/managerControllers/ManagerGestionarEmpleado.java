package controller.managerControllers;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import controller.browserControllers.BrowseEmployeesC;
import model.user.Employee;
import model.user.Permission;
import model.utilities.exceptions.PasswordNotValid;
import view.App;
import view.managerPanels.ManagerGestionEmplIndividual;
import view.miniPanels.EmployeeMiniP;
import view.miniPanels.UserMiniP;

public class ManagerGestionarEmpleado implements Controller{
    
    private UserMiniP employee;
    private App frame;
    private ManagerGestionEmplIndividual mge;
    
    public ManagerGestionarEmpleado(ManagerGestionEmplIndividual mng, App frame, UserMiniP emp) {
        this.mge = mng;
        this.frame = frame;
        this.employee = emp;
	}
	
	@Override
	public void initializeActions() {
		this.mge.getConfirmar().addActionListener(e->{
			String userName = this.mge.getUserName().getText();
			if(userName == null) return;
			String pwd = this.mge.getPwd().getText();
			if(pwd == null) return;
			
			List<Permission> perms = new ArrayList<>();
			
			if(mge.getStorePerm().isSelected()) {
				perms.add(Permission.STORE);
			}
			if(mge.getExchangePerm().isSelected()) {
				perms.add(Permission.EXCHANGE);
			}
			if(mge.getOrderPerm().isSelected()) {
				perms.add(Permission.ORDER);
			}
			
			if(perms.isEmpty()) return;
			
			Permission[] p = perms.toArray(new Permission[0]);
			
			Employee emp = (Employee)this.employee.getUser();
			emp.setPerm(p);
			
			emp.setUserName(userName);
			try {
				emp.changePassword(pwd);
			} catch (PasswordNotValid e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.frame.changeVisibleCard("GESTIONAR_EMPL");
		});}
}