package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.store.Store;
import view.banners.BannerManager;
import view.browserPanels.BrowsePacks;
import view.miniPanels.PackMiniEdit;
import view.miniPanels.PackMiniP;

public class ManagerGestionarPacks extends JPanel{
	
	private List<JButton> packsButtons = new ArrayList<>();
	private JTextField text = new JTextField(20);
	private BannerManager banner = new BannerManager();
	private JButton newPack = new JButton("Crear nuevo pack");
	
	public ManagerGestionarPacks(App app) {
		super();
		
		this.setLayout(new BorderLayout());
		this.add(banner, BorderLayout.NORTH);
    	
		JPanel list = new JPanel();
    	JPanel mainThings = new JPanel();
    	mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));
    	
    	List<Pack> packs = Store.getInstance().getPacks();
    	
    	JScrollPane scroll = new JScrollPane(mainThings);
    	
    	for(Pack p: packs) {
    		try {
				mainThings.add(new PackMiniEdit(p, 0));
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
    	}
    	
    	this.newPack.setPreferredSize(new Dimension(120, 30));
    	
    	JPanel auxiliar = new JPanel();
    	auxiliar.add(newPack);
    	
    	this.add(scroll, BorderLayout.CENTER);
    	this.add(auxiliar, BorderLayout.EAST);
    	
	}	
}
