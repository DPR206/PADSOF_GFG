package view;

import model.product.StoreProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static view.ImageAdder.getImageLabel;

public class BrowseStoreP extends JPanel {
    List<MiniProduct> productPanels = new ArrayList<>();
    List<StoreProduct> storeProducts = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowseStoreP() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        if (storeProducts != null) {
            for (StoreProduct product : storeProducts) {
                MiniProduct miniProduct = new MiniProduct(product);
                productPanels.add(miniProduct);
                this.add(miniProduct);
            }
        }

    }

    class MiniProduct extends JPanel {
        private final JButton addToCart = new JButton("Add to Cart");

        /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
        public MiniProduct(StoreProduct product) {
            this.setLayout(new FlowLayout());

            JLabel productImage = getImageLabel(product.getPhoto(), 100, 100); // DUE: Revisar dimensiones
            JTextPane productInfo = new JTextPane();
            productInfo.setEditable(false);
            productInfo.setText(
                    product.getName() + "\nPrecio: " + product.getPrice() + " €\nUnidades: " + product.getStock());
            productInfo.setPreferredSize(new Dimension(400, 100));
            //DUE: Añadir descuento al JLabel

            this.add(productImage);
            this.add(productInfo);
            this.add(addToCart);
        }

        /**
         * It makes it possible to assign a controller to this panel's components
         * @param c the desired controller
         */
        public void setController(ActionListener c) {
            addToCart.addActionListener(c);
        }
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        if (productPanels != null) {
            for (MiniProduct miniProduct : productPanels) {
                miniProduct.setController(c);
            }
        }
        //DUE
    }

    public void setStoreProducts(List<StoreProduct> newStoreProducts) {
        this.storeProducts = newStoreProducts;
    }
}