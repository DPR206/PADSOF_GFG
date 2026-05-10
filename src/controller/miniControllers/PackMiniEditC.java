package controller.miniControllers;

import controller.browserControllers.BrowserController;
import controller.browserControllers.MixedBrowserController;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.employeePanels.SPManageIndividualPack;
import view.browserPanels.BrowserPanel;
import view.browserPanels.MixedBrowserPanel;
import view.miniPanels.PackMiniEdit;
import view.miniPanels.PackMiniP;

import javax.swing.*;
import java.awt.event.*;

public class PackMiniEditC implements ActionListener {

    private App frame;
    private Store model;
    private PackMiniEdit view;
    private MixedBrowserController<Pack, StoreProduct> browserController;
    private MixedBrowserPanel<Pack, StoreProduct> browserPanel;
    private SPManageIndividualPack spm;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackMiniEditC(App frame, Store model, PackMiniEdit view,
            MixedBrowserController<Pack, StoreProduct> browserController,
            MixedBrowserPanel<Pack, StoreProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;
        this.spm = new SPManageIndividualPack(this.view.getPack());

        view.getPackImage().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del producto");
                }
            }
        });

        view.getPackInfo().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JOptionPane.showMessageDialog(frame, "Aquí se cambiaría a la página del pack");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Gestionar")) {
            //MOSTRAR EL PACK, DUE
        }

    }
}