package controller.employeeControllers;

import controller.Controller;
import controller.browserControllers.BrowseExchangesC;
import controller.browserControllers.BrowseValuationProductsC;
import controller.managerControllers.ManagePacksC;
import controller.managerControllers.ManagerManageProductsC;
import controller.managerControllers.ManagerNewProductC;
import model.store.Store;
import view.App;
import view.browserPanels.BrowseExchangesP;
import view.browserPanels.BrowseSecondHandProductsP;
import view.employeePanels.EmployeeMainP;
import view.managerPanels.ManagerGestionarPacks;
import view.managerPanels.ManagerGestionarProductos;
import view.managerPanels.ManagerNewProduct;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * The type Employee main c.
 * @author Ana O.R.
 * @version 1.0
 */
public class EmployeeMainC implements Controller {
    private final EmployeeMainP view;
    private final App frame;
    private ManagerGestionarPacks mgp = null;
    private ManagerGestionarProductos mproducts = null;
    private ManagerNewProduct create = null;
    
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * This controller's constructor
     * @param frame the controller's frame
     * @param model the controller's model
     */
    public EmployeeMainC(App frame, Store model) throws BadLocationException {
        this.frame = frame;
        this.view = frame.getEmployeeMainPanel();

        BrowseExchangesP exchangeView = new BrowseExchangesP();
        this.frame.addCard(exchangeView, "MANAGE_EXCHANGES");
        new BrowseExchangesC(this.frame, exchangeView, model);

        try {
            BrowseSecondHandProductsP valuateView = new BrowseSecondHandProductsP("Valuate", null);
            this.frame.addCard(valuateView, "BROWSE_VALUATION_PRODUCTS");
            new BrowseValuationProductsC(this.frame, valuateView, model);
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getManagePacks().addActionListener(
                e ->{
                	this.mgp = new ManagerGestionarPacks(this.frame);
                	new ManagePacksC(mgp, this.frame);
                	
                	this.frame.addCard(mgp, "EMPLOYEE MANAGE PACKS");
                	try {
						this.frame.changeVisibleCard("EMPLOYEE MANAGE PACKS");
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                });

        view.getManageStoreProducts().addActionListener(
                e -> {
                	
                	try {
						this.mproducts = new ManagerGestionarProductos(this.frame);
						new ManagerManageProductsC(mproducts, frame);
						
						this.frame.addCard(mproducts, "EMPLOYEE MANAGE STOREPRODUCTS");
						this.frame.changeVisibleCard("EMPLOYEE MANAGE STOREPRODUCTS");
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                });

        view.getAddStoreProducts().addActionListener(
                e -> {
                	this.create = new ManagerNewProduct();
                	new ManagerNewProductC(frame, create);
                	
                	this.frame.addCard(create, "EMPLOYEE CREATE PRODUCTS");
                	try {
						this.frame.changeVisibleCard("EMPLOYEE CREATE PRODUCT");
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
          );

        view.getManageExchanges().addActionListener(e -> {
            try {
                frame.changeVisibleCard("MANAGE_EXCHANGES");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getValuateProducts().addActionListener(e -> {
            try {
                frame.changeVisibleCard("BROWSE_VALUATION_PRODUCTS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}