package view.managerPanels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.store.Store;
import view.App;
import view.miniPanels.PackMiniEdit;

public class ManagerGestionarPacks extends JPanel{


	private JButton newPack = new JButton("Crear nuevo pack");

	public ManagerGestionarPacks(App app) {
		super();

		this.setLayout(new BorderLayout());

		JPanel mainThings = new JPanel();
    	mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));

    	List<Pack> packs = Store.getInstance().getPacks();

    	JScrollPane scroll = new JScrollPane(mainThings);

    	int index = 1;

    	for(Pack p: packs) {
    		try {
				mainThings.add(new PackMiniEdit(p, index));
				index++;
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

	public void setController(ActionListener c) {
		this.newPack.addActionListener(c);
	}
}