package view.managerPanels;

import model.product.Pack;
import view.browserPanels.BrowsePackProductsP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager individual simple pack.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerIndividualSimplePack extends JPanel {
    private Pack p;
    private JTextField NameProduct = new JTextField();
    private JTextField packPrecio = new JTextField();
    private JButton btnConfirmarProducto = new JButton("CONFIRMAR PRODUCTO");
    private JButton btnConfirmarPrecio = new JButton("CONFIRMAR PRECIO");
    private BrowsePackProductsP browser;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager individual simple pack.
     * @param p the p
     */
    public ManagerIndividualSimplePack(Pack p) {
        super();
        this.p = p;
        this.browser = new BrowsePackProductsP(p);

        this.setLayout(new BorderLayout());

        this.add(browser, BorderLayout.NORTH);

        JPanel inserciones = new JPanel();
        inserciones.setLayout(new GridLayout(6, 1));

        inserciones.add(new JLabel("NOMBRE DEL PRODUCTO A AÑADIR:"));
        inserciones.add(NameProduct);
        inserciones.add(this.btnConfirmarProducto);
        inserciones.add(new JLabel("PRECIO NUEVO:"));
        inserciones.add(packPrecio);
        inserciones.add(this.btnConfirmarPrecio);
        this.add(inserciones, BorderLayout.EAST);

        this.add(this.browser);
    }

    /**
     * It gets the browser
     * @return the browser
     */
    public BrowsePackProductsP getBrowser() {
        return this.browser;
    }

    /**
     * It gets the confirmar precio
     * @return the confirmar precio
     */
    public JButton getConfirmarPrecio() {
        return this.btnConfirmarPrecio;
    }

    /**
     * It gets the confirmar product
     * @return the confirmar product
     */
    public JButton getConfirmarProduct() {
        return this.btnConfirmarProducto;
    }

    /**
     * It gets the name product text
     * @return the name product text
     */
    public JTextField getNameProductText() {
        return this.NameProduct;
    }

    /**
     * It gets the pack price text
     * @return the pack price text
     */
    public JTextField getPackPriceText() {
        return this.packPrecio;
    }

    /**
     * It sets the controller confirmar precio
     * @param e the e
     */
    public void setControllerConfirmarPrecio(ActionListener e) {
        this.btnConfirmarPrecio.addActionListener(e);
    }

    /**
     * It sets the controller confirmar product
     * @param e the e
     */
    public void setControllerConfirmarProduct(ActionListener e) {
        this.btnConfirmarProducto.addActionListener(e);
    }
}