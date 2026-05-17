package view.managerPanels;

import model.product.Pack;
import view.browserPanels.BrowsePacksComposed;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager individual composed pack.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerIndividualComposedPack extends JPanel {
    private final JTextField idProduct = new JTextField();
    private final JTextField packPrecio = new JTextField();
    private final JTextField idPack = new JTextField();
    private final JButton btnConfirmarProducto = new JButton("CONFIRMAR PRODUCTO");
    private final JButton btnConfirmarPrecio = new JButton("CONFIRMAR PRECIO");
    private final JButton btnConfirmarPack = new JButton("CONFIRMAR PACK");
    private Pack p;
    private BrowsePacksComposed browser;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager individual composed pack.
     * @param p the p
     */
    public ManagerIndividualComposedPack(Pack p) {
        super();
        this.p = p;
        try {
            this.browser = new BrowsePacksComposed();
        } catch (BadLocationException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(new BorderLayout());

        this.add(browser, BorderLayout.NORTH);

        JPanel inserciones = new JPanel();
        inserciones.setLayout(new GridLayout(9, 1));

        inserciones.add(new JLabel("NOMBRE DEL PRODUCTO A AÑADIR:"));
        inserciones.add(idProduct);
        inserciones.add(btnConfirmarProducto);
        inserciones.add(new JLabel("PRECIO NUEVO:"));
        inserciones.add(packPrecio);
        inserciones.add(btnConfirmarPrecio);
        inserciones.add(new JLabel("ID DEL PACK A AÑADIR:"));
        inserciones.add(this.idPack);
        inserciones.add(this.btnConfirmarPack);
        this.add(inserciones, BorderLayout.EAST);

        this.add(this.browser);
    }

    /**
     * It gets the browser
     * @return the browser
     */
    public BrowsePacksComposed getBrowser() {
        return this.browser;
    }

    /**
     * It gets the confirmar pack id
     * @return the confirmar pack id
     */
    public JButton getConfirmarPackId() {
        return this.btnConfirmarPack;
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
     * It gets the id product text
     * @return the id product text
     */
    public JTextField getIdProductText() {
        return this.idProduct;
    }

    /**
     * It gets the pack id text
     * @return the pack id text
     */
    public JTextField getPackIdText() {
        return this.idPack;
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