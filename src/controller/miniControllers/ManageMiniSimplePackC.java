package controller.miniControllers;

import controller.Controller;
import view.managerPanels.ManagerIndividualSimplePack;
import view.miniPanels.PackMiniP;

public class ManageMiniSimplePackC implements Controller{
	
	private PackMiniP selected;
	private ManagerIndividualSimplePack mip;
	
	public ManageMiniSimplePackC(PackMiniP selected) {
		this.selected = selected;
	}
	
	@Override
	public void initializeActions() {
		this.selected.getButton().addActionListener(e->{
			this.mip = new ManagerIndividualSimplePack(selected.getPack());
			//CREAR CONTROLER 
		});
		
	}
	/*DUE*/ 
}
