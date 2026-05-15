package controller.managerControllers;

import javax.swing.JButton;

import controller.Controller;
import view.App;
import view.managerPanels.ManagerCreatePackP;
import view.managerPanels.ManagerCreateSimplePack;

public class ManageCreatePackC implements Controller{
	private ManagerCreatePackP panel;
	ManagerCreateSimplePack sp;
	private App frame;
	
	public ManageCreatePackC(ManagerCreatePackP panel, App frame) {
		this.panel = panel;
		this.frame = frame;
		initializeActions();
	}

	@Override
	public void initializeActions() {
		this.panel.getSimplePackButton().addActionListener(e->{
			sp = new ManagerCreateSimplePack();
			new ManagerCreateSimplePackC(sp, this.frame);
			this.frame.addCard(sp, "CREATING SIMPLE PACK");
			this.frame.changeVisibleCard("CREATING SIMPLE PACK");
		});
	}
}
