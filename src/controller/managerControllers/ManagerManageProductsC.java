package controller.managerControllers;

import controller.Controller;
import controller.browserControllers.BrowseStoreProductC;
import view.App;
import view.managerPanels.ManagerGestionarProductos;

import javax.swing.text.BadLocationException;

/**
 * The type Manager manage products c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class ManagerManageProductsC implements Controller {

    private final ManagerGestionarProductos mgproduct;
    private final App frame;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager manage products c.
     * @param mgproduct the mgproduct
     * @param frame     the frame
     */
    public ManagerManageProductsC(ManagerGestionarProductos mgproduct, App frame) {
        this.mgproduct = mgproduct;
        this.frame = frame;
        try {
            initializeActions();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initializeActions() throws BadLocationException {
        /*Inicializamos el controlador del browser*/
        new BrowseStoreProductC(this.mgproduct.getProductsPanel(), frame);
    }

}