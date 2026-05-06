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
        this.mge.setVisible(false);
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
        	
        }
        else if(e.getActionCommand().equals("Productos nuevos")) {
        	this.mgproduct.setVisible(true);
        }
        else if(e.getActionCommand().equals("Añadir productos")) {
        	this.mnproduct.setVisible(true);
        }
        else if(e.getActionCommand().equals("Empleados")) {
        	this.mge.setVisible(true);
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