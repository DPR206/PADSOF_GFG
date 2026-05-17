package controller.managerControllers;

import controller.Controller;
import model.store.Store;
import view.App;
import view.managerPanels.*;

import javax.swing.text.BadLocationException;

/**
 * The type Manager main c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerMainC implements Controller {
    private final ManagerMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final ManagerGestionarEmpleados mge;
    private final ManagerGestionarProductos mgproduct;
    private final ManagerNewProduct mnproduct;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager main c.
     * @param frame the frame
     * @param model the model
     * @throws BadLocationException the bad location exception
     */
    public ManagerMainC(App frame, Store model) throws BadLocationException {
        this.frame = frame;
        this.view = frame.getManagerMainPanel();
        this.model = model;

        ManagerGestionarPacks m = new ManagerGestionarPacks(frame);
        new ManagePacksC(m, this.frame);
        this.frame.addCard(m, "GEST_PACKS");

        this.mgproduct = new ManagerGestionarProductos(this.frame);
        new ManagerManageProductsC(this.mgproduct, this.frame);
        this.frame.addCard(this.mgproduct, "NUEVOS_PRODUCTOS");

        this.mnproduct = new ManagerNewProduct();
        new ManagerNewProductC(this.frame, this.mnproduct);
        this.frame.addCard(this.mnproduct, "CREAR_PRODUCTO");

        this.mge = new ManagerGestionarEmpleados(this.frame);
        new ManagerGestionarEmpleadosC(this.mge, this.frame);
        this.frame.addCard(this.mge, "GESTIONAR_EMPL");

        ManagerStatisticsP managerStatisticsP = new ManagerStatisticsP();
        new ManagerStatisticsC(managerStatisticsP);
        this.frame.addCard(managerStatisticsP, "STATISTICS");

        ManagerDiscountsP managerDiscountsP = new ManagerDiscountsP();
        new ManagerDiscountsC(frame, model, managerDiscountsP);
        this.frame.addCard(managerDiscountsP, "MANAGER_DISCOUNTS");

        ManagerParametersP managerParametersP = new ManagerParametersP();
        new ManagerParametersC(frame, managerParametersP);
        this.frame.addCard(managerParametersP, "MANAGER_PARAMETERS");

        initializeActions();
    }

    @Override
    public void initializeActions() {
        view.getPacks().addActionListener(e -> {
            try {
                this.frame.changeVisibleCard("GEST_PACKS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getProductoNuevo().addActionListener(e -> {
            try {
                this.frame.changeVisibleCard("NUEVOS_PRODUCTOS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getAnnadirProductos().addActionListener(e -> {
            try {
                this.frame.changeVisibleCard("CREAR_PRODUCTO");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getEmpleados().addActionListener(e -> {
            try {
                this.frame.changeVisibleCard("GESTIONAR_EMPL");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getEstadisticas().addActionListener(e -> {
            try {
                this.frame.changeVisibleCard("STATISTICS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getDescuentos().addActionListener(e -> {
            try {
                this.frame.changeVisibleCard("MANAGER_DISCOUNTS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });

        view.getParametros().addActionListener(e -> {
            try {
                this.frame.changeVisibleCard("MANAGER_PARAMETERS");
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}