package controller.managerControllers;

import controller.Controller;
import controller.browserControllers.MixedBrowseStoreEditC;
import model.store.Store;
import view.App;
import view.managerPanels.*;

import javax.swing.text.BadLocationException;

public class ManagerMainC implements Controller {
    private final ManagerMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final ManagerGestionarEmpleados mge;
    private final ManagerGestionarPacks mgp;
    private final ManagerGestionarProductos mgproduct;
    private final ManagerNewProduct mnproduct;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerMainC(App frame, Store model) throws BadLocationException {
        this.frame = frame;
        this.view = frame.getManagerMainPanel();
        this.model = model;
        this.mge = new ManagerGestionarEmpleados(this.frame);
        new ManagerGestionarEmpleadosC(this.mge, this.frame);
        //this.frame.addCard
        this.mgp = new ManagerGestionarPacks(this.frame);

        this.mgproduct = new ManagerGestionarProductos(this.frame);
        new MixedBrowseStoreEditC(this.frame, this.model, this.mgproduct.getProductsPanel());
        this.mgproduct.setVisible(false);
        this.mnproduct = new ManagerNewProduct();
        new ManagerNewProductC(this.frame, this.mnproduct);
        this.mnproduct.setVisible(false);

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getPacks().addActionListener(e -> {
            this.frame.addCard(this.mgp, "GEST_PACKS");
            this.frame.changeVisibleCard("GEST_PACKS");
        });

        view.getProductoNuevo().addActionListener(e -> {
            this.frame.addCard(this.mgproduct, "NUEVOS_PRODUCTOS");
            this.frame.changeVisibleCard("NUEVOS_PRODUCTOS");
        });

        view.getAnnadirProductos().addActionListener(e -> {
            this.frame.addCard(this.mnproduct, "CREAR_PRODUCTO");
            this.frame.changeVisibleCard("CREAR_PRODUCTO");
        });

        view.getEmpleados().addActionListener(e -> {
            this.frame.addCard(this.mge, "GESTIONAR_EMPL");
            this.frame.changeVisibleCard("GESTIONAR_EMPL");
        });

        view.getEstadisticas().addActionListener(e -> {
            ManagerStatisticsP managerStatisticsP = new ManagerStatisticsP();
            new ManagerStatisticsC(frame, model, managerStatisticsP);
            this.frame.addCard(managerStatisticsP, "STATISTICS");
            this.frame.changeVisibleCard("STATISTICS");
        });

        view.getDescuentos().addActionListener(e -> {
            ManagerDiscountsP managerDiscountsP = new ManagerDiscountsP();
            new ManagerDiscountsC(frame, model, managerDiscountsP);
            this.frame.addCard(managerDiscountsP, "MANAGER_DISCOUNTS");
            this.frame.changeVisibleCard("MANAGER_DISCOUNTS");
        });

        view.getParametros().addActionListener(e -> {
            ManagerParametersP managerParametersP = new ManagerParametersP();
            new ManagerParametersC(frame, model, managerParametersP);
            this.frame.addCard(managerParametersP, "MANAGER_PARAMETERS");
            this.frame.changeVisibleCard("MANAGER_PARAMETERS");
        });
    }
}