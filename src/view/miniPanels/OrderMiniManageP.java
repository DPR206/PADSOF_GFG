package view.miniPanels;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.Serial;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.order.Order;

public class OrderMiniManageP extends AbstractMiniP{
	@Serial
    private static final long serialVersionUID = 1L;
    private final JLabel orderState = new JLabel("");
    private final JButton addReview = new JButton("MANAGE");
    private final JTextPane productInfo;
    private Order o;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Order mini.
     * @param index the index
     */
    public OrderMiniManageP(int index, Order o) {
    	this.o = o;
        int height = 100;

        this.setLayout(new BorderLayout(15, 0));

        Border lineaMarron = BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour);
        Border margenInterno = BorderFactory.createEmptyBorder(10, 15, 10, 15);
        this.setBorder(BorderFactory.createCompoundBorder(lineaMarron, margenInterno));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

        JLabel indexLabel = new JLabel(index + ".");
        indexLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        indexLabel.setPreferredSize(new Dimension(25, 50));

        JLabel productImage = getImageLabel(".\\resources\\app\\order.png", 70, 70);

        leftPanel.add(indexLabel);
        leftPanel.add(productImage);

        productInfo = new JTextPane();
        productInfo.setEditable(false);
        productInfo.setFocusable(false);
        productInfo.setContentType("text/html");
        productInfo.setOpaque(false);

        orderState.setFont(new Font("SansSerif", Font.BOLD, 12));
        orderState.setHorizontalAlignment(SwingConstants.CENTER);
        orderState.setOpaque(true);
        orderState.setBackground(new Color(240, 240, 240));
        orderState.setForeground(brownColour);
        orderState.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(brownColour, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        addReview.setFont(new Font("SansSerif", Font.BOLD, 14));
        addReview.setPreferredSize(new Dimension(25, height));

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(2, 0, 2, 0);

        gbc.gridy = 0;
        rightPanel.add(orderState, gbc);

        gbc.gridy = 1;
        addReview.setFont(new Font("SansSerif", Font.BOLD, 13));
        rightPanel.add(addReview, gbc);

        // --- AÑADIR AL PANEL PRINCIPAL ---
        this.add(leftPanel, BorderLayout.WEST);
        this.add(productInfo, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);

        this.setPreferredSize(new Dimension(0, height));
    }

    /**
     * It gets the add review
     * @return the addReview
     */
    public JButton getAddReview() {
        return addReview;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        addReview.addActionListener(c);
    }

    /**
     * It sets the order details
     * @param listaProductos the lista productos
     * @param precio         the precio
     */
    public void setOrderDetails(String listaProductos, double precio) {
        // El estilo se define aquí directamente porque el HTML sobrescribe todo lo anterior
        productInfo.setText("<html>" + "<body style='font-family: SansSerif;'>" + "<div style='padding-top: 5px;'>" +
                            "<b>Products:</b> " + listaProductos + "<br>" + "<span style='color: #5D4037;'><b>Total: " +
                            String.format("%.2f", precio) + "€</b></span>" + "</div>" + "</body>" + "</html>");
    }

    /**
     * It sets the order state
     * @param state the state
     */
    public void setOrderState(String state) {
        this.orderState.setText("Status: " + state);
    }
    
    public JButton getButton() {
    	return this.addReview;
    }
    public Order getItem() {
    	return this.o;
    }
}
