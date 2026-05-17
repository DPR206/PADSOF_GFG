package view.employeePanels;

import model.product.Pack;
import model.product.StoreProduct;
import view.miniPanels.PackMiniP;
import view.miniPanels.StoreProductMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.util.HashSet;
import java.util.List;

/**
 * The type Sp manage individual pack.
 * @author Sofia C.L.
 * @version 1.0
 */
public class SPManageIndividualPack extends JPanel {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Sp manage individual pack.
     * @param p the p
     */
    public SPManageIndividualPack(Pack p) {

        this.setLayout(new BorderLayout());

        JPanel scrollPanels = new JPanel();
        scrollPanels.setLayout(new GridLayout(2, 0));

        JPanel aux1 = new JPanel();
        JPanel aux2 = new JPanel();

        //añado los minipacks

        HashSet<Pack> listPacks = p.getPacks();

        int i = 1;

        for (Pack pack : listPacks) {
            try {
                aux1.add(new PackMiniP(pack, i, "Manage", null));
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
        JScrollPane scroll1 = new JScrollPane(aux1);

        //añado los miniproductos en el otro

        i = 1;

        List<StoreProduct> products = p.getProducts();
        for (StoreProduct sp : products) {
            try {
                aux2.add(new StoreProductMiniP(sp, i, "Delete from pack", ".\\resources\\app\\cart.png"));
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
            i++;
        }

        JScrollPane scroll2 = new JScrollPane(aux2);
        scrollPanels.add(scroll1);
        scrollPanels.add(scroll2);

        this.add(scrollPanels, BorderLayout.CENTER);
        JPanel panelCreacion = new JPanel();

        panelCreacion.setBackground(new Color(245, 241, 236)); // fondo beige claro
        panelCreacion.setLayout(new BorderLayout(10, 10));

        JPanel camposPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        camposPanel.setBackground(new Color(245, 241, 236));

        JLabel lblProducto = new JLabel("Añadir producto");
        JButton btnConfirmarProducto = new JButton("CONFIRMAR PRODUCTO");
        btnConfirmarProducto.setForeground(Color.WHITE);
        JLabel precioPack = new JLabel("Precio del pack");
        camposPanel.add(lblProducto);
        JTextField nameProduct = new JTextField();
        camposPanel.add(nameProduct);
        camposPanel.add(btnConfirmarProducto);
        camposPanel.add(precioPack);
        JTextField packPrecio = new JTextField();
        camposPanel.add(packPrecio);
        JButton btnConfirmarPrecio = new JButton("CONFIRMAR PRECIO");
        camposPanel.add(btnConfirmarPrecio);

        this.add(camposPanel, BorderLayout.SOUTH);
    }
}