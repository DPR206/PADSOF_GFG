package controller.managerControllers;

import model.store.Store;
import view.App;
import view.managerPanels.ManagerGestionarEmpleados;
import view.managerPanels.ManagerGestionarPacks;
import view.managerPanels.ManagerGestionarProductos;
import view.managerPanels.ManagerMainP;
import view.managerPanels.ManagerNewProduct;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMainC implements ActionListener {
    private final ManagerMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final ManagerGestionarEmpleados mge;
    private final ManagerGestionarPacks mgp;
    private final ManagerGestionarProductos mgproduct;
    private final ManagerNewProduct mnproduct;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getManagerMainPanel();
        this.model = model;
        this.mge = new ManagerGestionarEmpleados(this.frame);
        //this.frame.addCard
        this.mgp = new ManagerGestionarPacks(this.frame);
        this.mgp.setVisible(false);
        this.mgproduct = new ManagerGestionarProductos(this.frame);
        this.mgproduct.setVisible(false);
        this.mnproduct = new ManagerNewProduct();
        this.mnproduct.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Packs")) {
        	this.frame.addCard(this.mgp, "GEST_PACKS");
        	this.frame.changeVisibleCard("GEST_PACKS");
        }
        else if(e.getActionCommand().equals("Productos nuevos")) {
        	this.frame.addCard(this.mgproduct, "NUEVOS_PRODUCTOS");
        	this.frame.changeVisibleCard("NUEVOS_PRODUCTOS");
        }
        else if(e.getActionCommand().equals("Añadir productos")) {
        	this.frame.addCard(this.mnproduct, "CREAR_PRODUCTO");
        	this.frame.changeVisibleCard("CREAR_PRODUCTO");
        }
        else if(e.getActionCommand().equals("Empleados")) {
        	this.frame.addCard(this.mge, "GESTIONAR_EMPL");
        	this.frame.changeVisibleCard("GESTIONAR_EMPL");
        }
        else if(e.getActionCommand().equals("Estadísticas")) {
        	//DUE
        }
        else if(e.getActionCommand().equals("Descuentos")) {
        	//DUE
        }
        else if(e.getActionCommand().equals("Parámetros")) {
        	//DUE
        }
    }
}