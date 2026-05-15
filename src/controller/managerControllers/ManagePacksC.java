package controller.managerControllers;

import java.util.*;

import javax.swing.text.BadLocationException;

import controller.Controller;
import model.product.ComposedPack;
import model.product.Pack;
import model.product.SimplePack;
import model.store.Store;
import view.App;
import view.managerPanels.ManagerCreatePackP;
import view.managerPanels.ManagerGestionarPacks;
import view.managerPanels.ManagerIndividualComposedPack;
import view.managerPanels.ManagerIndividualSimplePack;
import view.miniPanels.AbstractMiniP;
import view.miniPanels.PackMiniP;

public class ManagePacksC implements Controller{
	private ManagerGestionarPacks gestionar;
	private App frame;
	private ManagerCreatePackP packP = new ManagerCreatePackP();
	
	public ManagePacksC(ManagerGestionarPacks m, App frame) {
		this.gestionar = m;
		this.frame = frame;
		int i = 1;
		
		for(Pack p: Store.getInstance().getPacks()) {
			try {
				this.gestionar.getBrowser().addMiniPanel(p, i);
				i++;
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (AbstractMiniP ap : this.gestionar.getBrowser().getMiniPanels()) {
			PackMiniP miniPack = (PackMiniP) ap;
			miniPack.setController(e -> {
				Pack pack = miniPack.getPack();
				if (pack instanceof ComposedPack) {
					ManagerIndividualComposedPack misp = new ManagerIndividualComposedPack(pack);
					this.frame.addCard(misp, "COMPOSED PACKS");
					this.frame.changeVisibleCard("COMPOSED PACKS");}
				else if (pack instanceof SimplePack || !(pack instanceof ComposedPack)) {
					ManagerIndividualSimplePack misp = new ManagerIndividualSimplePack(pack);
					new ManagerGestPackSimpleInd(pack, misp);
					this.frame.addCard(misp, "SIMPLE PACKS");
					this.frame.changeVisibleCard("SIMPLE PACKS"); 
				}
			});
		}
		
		initializeActions();
	}
	
	@Override
	public void initializeActions() {
		this.gestionar.getConfirmacion().addActionListener(e->{
			frame.addCard(packP, "CREAR NUEVO PACK");
			new ManageCreatePackC(packP, frame);
			this.frame.changeVisibleCard("CREAR NUEVO PACK");
		});	
	}
	public void addPack() {
		int i = 1;
		for(Pack p: Store.getInstance().getPacks()) {
			try {
				this.gestionar.getBrowser().addMiniPanel(p, i);
				i++;
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}