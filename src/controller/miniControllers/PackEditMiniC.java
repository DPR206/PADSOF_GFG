package controller.miniControllers;

import controller.Controller;
import controller.browserControllers.AbstractMixedBrowserC;
import model.product.Pack;
import model.product.StoreProduct;
import model.store.Store;
import view.App;
import view.browserPanels.AbstractMixedBrowserP;
import view.employeePanels.SPManageIndividualPack;
import view.miniPanels.PackMiniP;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Pack edit mini c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class PackEditMiniC implements Controller {

    private final App frame;
    private final PackMiniP view;
    private Store model;
    private AbstractMixedBrowserC<Pack, StoreProduct> browserController;
    private AbstractMixedBrowserP<Pack, StoreProduct> browserPanel;
    private SPManageIndividualPack spm;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Pack edit mini c.
     * @param frame             the frame
     * @param model             the model
     * @param view              the view
     * @param browserController the browser controller
     * @param browserPanel      the browser panel
     */
    public PackEditMiniC(App frame, Store model, PackMiniP view,
                         AbstractMixedBrowserC<Pack, StoreProduct> browserController,
                         AbstractMixedBrowserP<Pack, StoreProduct> browserPanel) {
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