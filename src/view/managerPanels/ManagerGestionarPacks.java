package view.managerPanels;

import model.product.Pack;
import model.store.Store;
import view.App;
import view.browserPanels.BrowsePacksP;
import view.miniPanels.PackMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ManagerGestionarPacks extends JPanel {
	
	private BrowsePacksP browser;
    private JButton newPack = new JButton("Crear nuevo pack");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerGestionarPacks(App app) {
        super();
        
        try {
			this.browser = new BrowsePacksP("MANAGE", ".\\resources\\app\\arrow_right.png");
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
        this.setLayout(new BorderLayout());

        JPanel mainThings = new JPanel();
        mainThings.setLayout(new BoxLayout(mainThings, BoxLayout.Y_AXIS));
        /**Esto lo hará el controlador
        List<Pack> packs = Store.getInstance().getPacks();

        int index = 1;

        for (Pack p : packs) {
            try {
                mainThings.add(new PackMiniP(p, index, "Manage", null));
                index++;
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }*/

        this.newPack.setPreferredSize(new Dimension(120, 30));

        JPanel auxiliar = new JPanel();
        auxiliar.add(newPack);

        this.add(this.browser, BorderLayout.CENTER);
        this.add(auxiliar, BorderLayout.EAST);

    }

    public void setController(ActionListener c) {
        this.newPack.addActionListener(c);
    }
    
    public JButton getConfirmacion() {
    	return this.newPack;
    }
    
    public BrowsePacksP getBrowser() {
    	return this.browser;
    }
}