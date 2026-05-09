package controller;

import model.store.Store;
import view.App;
import view.ManagerGestionarEmpleados;
import view.ManagerGestionarPacks;
import view.ManagerGestionarProductos;
import view.ManagerMainP;
import view.ManagerNewProduct;

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
        this.mgp = new ManagerGestionarPacks(this.frame);
        
        this.mgproduct = new ManagerGestionarProductos(this.frame);
        this.mnproduct = new ManagerNewProduct();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Packs")) {
        	this.frame.addCard(mge, "GESTIONAR_PACKS");
        	this.frame.changeVisibleCard("GESTIONAR_PACKS");
        }
        else if(e.getActionCommand().equals("Productos nuevos")) {
        	this.frame.addCard(mgproduct, "GESTIONAR_PRODUCTOS");
        	this.frame.changeVisibleCard("GESTIONAR_PRODUCTOS");
        }
        else if(e.getActionCommand().equals("Añadir productos")) {
        	this.frame.addCard(mnproduct, "CREAR_PRODUCTO");
        	this.frame.changeVisibleCard("CREAR_PRODUCTO");
        }
        else if(e.getActionCommand().equals("Empleados")) {
        	this.frame.addCard(mge, "GESTIONAR_EMPLEADOS");
        	this.frame.changeVisibleCard("GESTIONAR_EMPLEADOS");
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