package controller;

import model.store.Store;
import view.App;
import view.ManagerGestionarEmpleados;
import view.ManagerGestionarPacks;
import view.ManagerGestionarProductos;
import view.ManagerMainP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerMainC implements ActionListener {
    private final ManagerMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final ManagerGestionarEmpleados mge; 
    private final ManagerGestionarPacks mgp;
    private final ManagerGestionarProductos mgproduct;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getManagerMainPanel();
        this.model = model;
        this.mge = new ManagerGestionarEmpleados(this.frame);
        this.mgp = new ManagerGestionarPacks(this.frame);
        this.mgproduct = new ManagerGestionarProductos(this.frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Packs")) {
        	
        }
        else if(e.getActionCommand().equals("Productos nuevos")) {
        	
        }
        else if(e.getActionCommand().equals("Añadir productos")) {
        	
        }
        else if(e.getActionCommand().equals("Empleados")) {
        	
        }
        else if(e.getActionCommand().equals("Estadísticas")) {
        	
        }
        else if(e.getActionCommand().equals("Descuentos")) {
        	
        }
        else if(e.getActionCommand().equals("Parámetros")) {
        	
        }
    }
}