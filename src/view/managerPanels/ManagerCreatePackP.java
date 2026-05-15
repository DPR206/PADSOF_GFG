package view.managerPanels;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ManagerCreatePackP extends JPanel{
	private JButton createComposedPack = new JButton("CREATE COMPOSED PACK");
	private JButton createSimplePack = new JButton("CREATE SIMPLE PACK");
	
	public ManagerCreatePackP() {
		super();
		this.setLayout(new GridLayout(1,2));
		this.add(createComposedPack);
		this.add(createSimplePack);
	}
	
	public JButton getSimplePackButton() {
		return this.createSimplePack;
	}
	
	public JButton getComposedPackButton() {
		return this.createComposedPack;
	}
	
	public void setControllerComposed(ActionListener e) {
		this.createComposedPack.addActionListener(e);
	}
	
	public void setControllerSimple(ActionListener e) {
		this.createSimplePack.addActionListener(e);
	}
}
