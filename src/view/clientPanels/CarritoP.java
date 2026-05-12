package view.clientPanels;

import view.browserPanels.MixedBrowseCartP;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.text.BadLocationException;

public class CarritoP extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelLateral;
	private JButton btnOrders = new JButton("Orders");
	private JLabel total = new JLabel("Total: 0.00 €");
	private JButton btnPay = new JButton("Pay");
	private JButton btnDeleteAll = new JButton("Delete all");
	private MixedBrowseCartP cartItems;

	/**
	 *
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

        panelLateral = new JPanel(new BorderLayout());
        panelLateral.setPreferredSize(new Dimension(150, 0));
        panelLateral.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));

        panelLateral.add(panelNorte, BorderLayout.NORTH);
        panelLateral.add(panelSur, BorderLayout.SOUTH);

        this.add(panelLateral, BorderLayout.EAST);

        // Aquí iría el centro del carrito (donde se listan los productos)
        // this.add(scrollProductos, BorderLayout.CENTER);
		cartItems = new MixedBrowseCartP();
		this.add(cartItems,  BorderLayout.CENTER);

	}

	private Icon getScaledImage(ImageIcon imageIcon, int width, int height) {
	    if (imageIcon == null || imageIcon.getImage() == null) {
	        return null;
	    }

	    Image img = imageIcon.getImage();
	    Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    return new ImageIcon(scaledImg);
	}

	public MixedBrowseCartP getCartItems() {
		return cartItems;
	}

	/**
     * Permite actualizar el texto del total desde el controlador
     */
    public void setTotal(double nuevoTotal) {
        this.total.setText(String.format("Total: %.2f €", nuevoTotal));
    }

    public JButton getBtnOrders() { return btnOrders; }
    public JButton getBtnPay() { return btnPay; }
    public JButton getBtnDeleteAll() { return btnDeleteAll; }

}