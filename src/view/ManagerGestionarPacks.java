package view;

import java.awt.BorderLayout;
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
import view.miniPanels.PackMiniP;

public class ManagerGestionarPacks extends JPanel{
	
	private List<JButton> packsButtons = new ArrayList<>();
	private JTextField text = new JTextField(20);
	private BannerManager banner = new BannerManager();
	
	public ManagerGestionarPacks() {
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
				mainThings.add(new PackMiniP(p, 0));
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
    	}
    	
    	this.add(scroll, BorderLayout.CENTER);
    	
	}	
}
