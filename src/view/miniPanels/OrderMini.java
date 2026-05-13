package view.miniPanels;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class OrderMini extends AbstractMiniP {

	private static final long serialVersionUID = 1L;
    private JLabel orderState = new JLabel("");
    private JButton addReview = new JButton("Add Review");
    private JLabel productImage;
    private JTextPane productInfo;
    
	/**
	 * 
	 */
	public OrderMini(int index) {
		
        int height = 100;
        
        this.setLayout(new BorderLayout(15, 0));
        
        Border lineaMarron = BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour);
        Border margenInterno = BorderFactory.createEmptyBorder(10, 15, 10, 15);
        this.setBorder(BorderFactory.createCompoundBorder(lineaMarron, margenInterno));
		
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        
        JLabel indexLabel = new JLabel(index + ".");
        indexLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        indexLabel.setPreferredSize(new Dimension(25, 50));
        
        productImage = getImageLabel(".\\resources\\app\\order.png", 70, 70);
        
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
        orderState.setBorder(BorderFactory.createCompoundBorder(
           BorderFactory.createLineBorder(brownColour, 1),
           BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        
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
	 * @return the addReview
	 */
	public JButton getAddReview() {
		return addReview;
	}



	public void setOrderDetails(String listaProductos, double precio) {
        // El estilo se define aquí directamente porque el HTML sobrescribe todo lo anterior
        productInfo.setText(
            "<html>" +
            "<body style='font-family: SansSerif;'>" +
                "<div style='padding-top: 5px;'>" +
                    "<b>Products:</b> " + listaProductos + "<br>" +
                    "<span style='color: #5D4037;'><b>Total: " + String.format("%.2f", precio) + "€</b></span>" +
                "</div>" +
            "</body>" +
            "</html>"
        );
    }

    public void setOrderState(String state) {
        this.orderState.setText("Status: " + state);
    }
    
	/**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        addReview.addActionListener(c);
    }
}
