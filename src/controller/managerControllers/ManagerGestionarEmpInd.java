package controller.managerControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.user.Employee;
import model.user.ExchangePermission;
import model.user.OrderPermission;
import model.user.StorePermission;
import model.utilities.exceptions.PasswordNotValid;
import view.managerPanels.ManagerGestionEmplIndividual;

public class ManagerGestionarEmpInd implements ActionListener{
	private Employee emp;
	private ManagerGestionEmplIndividual mgei;
	
	public ManagerGestionarEmpInd(Employee emp, ManagerGestionEmplIndividual mgei){
		this.mgei = mgei;
		this.emp = emp;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("CONFIRMAR")) {
			String userName = mgei.getUserName().getText();
			if(userName == null) return;
			String pwd = mgei.getPwd().getText();
			if(pwd == null) return;
			
			emp.setUserName(userName);
			try {
				emp.changePassword(pwd);
			} catch (PasswordNotValid e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*Ahora revisamos los permisos*/
			if(mgei.getStorePerm().isSelected()) emp.setSp(new StorePermission());
			if(mgei.getOrderPerm().isSelected()) emp.setOp(new OrderPermission(false));
			if(mgei.getExchangePerm().isSelected()) emp.setEp(new ExchangePermission());
		}
		
	}
}
