package view;

import model.product.StoreProduct;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BrowseStoreP extends JPanel {
    List<StoreProductMiniP> productPanels = new ArrayList<>();
    List<StoreProduct> storeProducts;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowseStoreP(List<StoreProduct> storeProducts) throws BadLocationException {
        this.storeProducts = storeProducts;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        int index = 1;
        for (StoreProduct product : storeProducts) {
            StoreProductMiniP miniProduct = new StoreProductMiniP(product, index);
            productPanels.add(miniProduct);
            this.add(miniProduct);
            index++;
        }

    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        if (productPanels != null) {
            for (StoreProductMiniP miniProduct : productPanels) {
                miniProduct.setController(c);
            }
        }
        //DUE
    }

    public void setStoreProducts(List<StoreProduct> newStoreProducts) {
        this.storeProducts = newStoreProducts;
    }
}