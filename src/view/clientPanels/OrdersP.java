package view.clientPanels;

import view.browserPanels.BrowseOrdersP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.Serial;

/**
 * The type Orders p.
 * @author Duna P.R.
 * @version 1.0
 */
public class OrdersP extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private BrowseOrdersP orderItems;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Orders p.
     * @throws BadLocationException the bad location exception
     */
    public OrdersP() throws BadLocationException {

        this.setLayout(new BorderLayout());
        try {
            incializarEstrctura();
        } catch (BadLocationException e) {
            System.err.println("Error al inicializar la vista de pedidos: " + e.getMessage());
            this.add(new JLabel("Could not load orders. Please try again."), BorderLayout.CENTER);
        }
    }

    private void incializarEstrctura() throws BadLocationException {

        orderItems = new BrowseOrdersP();
        this.add(orderItems, BorderLayout.CENTER);

    }

    /**
     * It gets the order items
     * @return the order items
     */
    public BrowseOrdersP getOrderItems() {
        return orderItems;
    }
}