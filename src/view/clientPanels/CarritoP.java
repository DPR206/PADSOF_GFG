package view.clientPanels;

import view.browserPanels.MixedBrowseCartP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.Serial;

/**
 * The type Carrito p.
 * @author Duna P.R.
 * @version 1.0
 */
public class CarritoP extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton btnOrders = new JButton("Orders");
    private final JLabel total = new JLabel("Total: 0.00 €");
    private final JButton btnPay = new JButton("Pay");
    private final JButton btnDeleteAll = new JButton("Delete all");
    private MixedBrowseCartP cartItems;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Carrito p.
     * @throws BadLocationException the bad location exception
     */
    public CarritoP() throws BadLocationException {

        this.setLayout(new BorderLayout());
        incializarEstrctura();
    }

    private void incializarEstrctura() throws BadLocationException {

        btnOrders.setIcon(getScaledImage(new ImageIcon("./resources/app/order.png"), 32, 32));
        btnOrders.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnOrders.setHorizontalTextPosition(SwingConstants.CENTER);

        JPanel panelNorte = new JPanel(new GridLayout(0, 1, 5, 5));
        panelNorte.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 10));
        panelNorte.add(btnOrders);

        JPanel panelSur = new JPanel(new GridLayout(0, 1, 10, 10));
        panelSur.setBorder(BorderFactory.createEmptyBorder(0, 5, 20, 10));

        total.setFont(new Font("SansSerif", Font.BOLD, 16));
        total.setHorizontalAlignment(SwingConstants.CENTER);

        panelSur.add(total);
        panelSur.add(btnPay);
        panelSur.add(btnDeleteAll);

        JPanel panelLateral = new JPanel(new BorderLayout());
        panelLateral.setPreferredSize(new Dimension(150, 0));
        panelLateral.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));

        panelLateral.add(panelNorte, BorderLayout.NORTH);
        panelLateral.add(panelSur, BorderLayout.SOUTH);

        this.add(panelLateral, BorderLayout.EAST);

        cartItems = new MixedBrowseCartP();
        this.add(cartItems, BorderLayout.CENTER);

    }

    /**
     * It gets the btn delete all
     * @return the btn delete all
     */
    public JButton getBtnDeleteAll() {
        return btnDeleteAll;
    }

    /**
     * It gets the btn orders
     * @return the btn orders
     */
    public JButton getBtnOrders() {
        return btnOrders;
    }

    /**
     * It gets the btn pay
     * @return the btn pay
     */
    public JButton getBtnPay() {
        return btnPay;
    }

    /**
     * It gets the cart items
     * @return the cart items
     */
    public MixedBrowseCartP getCartItems() {
        return cartItems;
    }

    private Icon getScaledImage(ImageIcon imageIcon, int width, int height) {
        if (imageIcon == null || imageIcon.getImage() == null) {
            return null;
        }

        Image img = imageIcon.getImage();
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    /**
     * Permite actualizar el texto del total desde el controlador
     * @param nuevoTotal the nuevo total
     */
    public void setTotal(double nuevoTotal) {
        this.total.setText(String.format("Total: %.2f €", nuevoTotal));
    }

}