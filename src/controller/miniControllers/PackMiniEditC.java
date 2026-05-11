package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowserController;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.MixedBrowserPanel;
import view.employeePanels.SPManageIndividualPack;
import view.miniPanels.PackMiniP;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PackMiniEditC implements Controller {

    private App frame;
    private Store model;
    private PackMiniP view;
    private MixedBrowserController<Pack, StoreProduct> browserController;
    private MixedBrowserPanel<Pack, StoreProduct> browserPanel;
    private SPManageIndividualPack spm;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public PackMiniEditC(App frame, Store model, PackMiniP view,
                         MixedBrowserController<Pack, StoreProduct> browserController,
                         MixedBrowserPanel<Pack, StoreProduct> browserPanel) {
        this.frame = frame;
        this.view = view;
        this.model = model;
        this.browserController = browserController;
        this.browserPanel = browserPanel;
        this.spm = new SPManageIndividualPack(this.view.getPack());

        initializeActions();
    }

    @Override
    public void initializeActions() {
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

        view.getButton().addActionListener(e -> {
            //MOSTRAR EL PACK, DUE
        });
    }
}