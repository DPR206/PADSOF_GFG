package controller.managerControllers;

import controller.Controller;
import model.product.Pack;
import view.managerPanels.ManagerIndividualPack;

public class ManageIndivualPackC implements Controller{
	
	private ManagerIndividualPack mip;
	private Pack p;
	
	public ManageIndivualPackC(Pack p, ManagerIndividualPack mip) {
		this.mip = mip;
		this.p = p;
	}
	
	@Override
	public void initializeActions() {
		for(StoreProductP sp: this.p)
		
	} 

}
