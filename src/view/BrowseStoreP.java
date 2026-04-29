package view;

import model.product.StoreProduct;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

public class BrowseStoreP extends JPanel {
    List<MiniProduct> productPanels = new ArrayList<>();
    List<StoreProduct> storeProducts = new ArrayList<>();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public BrowseStoreP() throws BadLocationException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Change this

        /*storeProducts.add(new Comic(10.50, "Tintin: Aterrizaje en la Luna", "Comic de Tintin",
                ".\\resources\\tintin_aterrizaje_luna" + ".jpg", 5, 135, Year.parse("2000"), "Herge", "No clue"));
        storeProducts.add(
                new Game(8.75, "Virus", "Juego de cartas muy divertido", ".\\resources\\virus.jpg", 2, 3, "+3",
                        GameStyle.CARDS));*/

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
        public MiniProduct(StoreProduct product) throws BadLocationException {
            int width = 350;
            int height = 100;
            this.setLayout(new FlowLayout());

            addToCart.setPreferredSize(new Dimension(height + 50, height));
            addToCart.setIcon(getScaledImage(new ImageIcon(".\\resources\\cart.png"), height / 4, height / 4));

            JLabel productImage = getImageLabel(product.getPhoto(), height, height); // DUE: Revisar dimensiones
            JTextPane productInfo = new JTextPane();
            productInfo.setEditable(false);
            productInfo.setFocusable(false);

            SimpleAttributeSet attributes = new SimpleAttributeSet();
            StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
            StyleConstants.setBold(attributes, true);
            productInfo.setCharacterAttributes(attributes, true);
            productInfo.setText(product.getName() + "\n");

            attributes = new SimpleAttributeSet();
            StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);

            Document doc = productInfo.getStyledDocument();
            doc.insertString(doc.getLength(),
                    ("Price: " + String.format("%.2f", product.getPrice()) + " €\nStock: " + product.getStock()),
                    attributes);

            productInfo.setPreferredSize(new Dimension(width, height));
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