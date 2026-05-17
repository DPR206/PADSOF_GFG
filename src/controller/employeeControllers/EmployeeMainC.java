package controller.employeeControllers;

import controller.Controller;
import controller.browserControllers.BrowseValuationProductsC;
import controller.managerControllers.ManagePacksC;
import controller.managerControllers.ManagerManageProductsC;
import controller.managerControllers.ManagerNewProductC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseSecondHandProductsP;
import view.employeePanels.EmployeeMainP;
import view.managerPanels.ManagerGestionarPacks;
import view.managerPanels.ManagerGestionarProductos;
import view.managerPanels.ManagerNewProduct;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class EmployeeMainC implements Controller {
    private final EmployeeMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private ManagerGestionarPacks mgp = null;
    private ManagerGestionarProductos manageProducts = null;
    private ManagerNewProduct createProduct = null;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public EmployeeMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getEmployeeMainPanel();
        this.model = model;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getManagePacks().addActionListener(e -> {
        	this.mgp = new ManagerGestionarPacks(this.frame);
            new ManagePacksC(this.mgp, this.frame);
            
            this.frame.addCard(this.mgp, "EMPLOYEE GESTIONAR PACKS");
            this.frame.changeVisibleCard("EMPLOYEE GESTIONAR PACKS");
        });

        view.getManageStoreProducts().addActionListener(e -> {
            try {
				this.manageProducts = new ManagerGestionarProductos(this.frame);
				new ManagerManageProductsC(this.manageProducts, this.frame);
				
				this.frame.addCard(this.manageProducts,"EMPLOYEE MANAGE PRODUCTS");
				this.frame.changeVisibleCard("EMPLOYEE MANAGE PRODUCTS");
				
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        });

        view.getAddStoreProducts().addActionListener(e -> {
            this.createProduct = new ManagerNewProduct();
            new ManagerNewProductC(this.frame, this.createProduct);
            
            this.frame.addCard(this.createProduct, "EMPLOYEE CREATE PRODUCT");
            this.frame.changeVisibleCard("EMPLOYEE CREATE PRODUCT");
        });

        view.getManageExchanges().addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Aquí iría el panel de Manage Exchanges", "Manage Exchange",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        view.getValuateProducts().addActionListener(e -> {
            try {
                BrowseSecondHandProductsP browseSecondHandProductsP = new BrowseSecondHandProductsP("Valuate", null);
                frame.addCard(browseSecondHandProductsP, "BROWSE_VALUATION_PRODUCTS");
                frame.changeVisibleCard("BROWSE_VALUATION_PRODUCTS");
                new BrowseValuationProductsC(frame, browseSecondHandProductsP, model);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}