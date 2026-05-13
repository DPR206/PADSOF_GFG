package view.miniPanels;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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
		
        int height = 80;
        
		this.setLayout(new FlowLayout());
		
		productImage = getImageLabel(".\\resources\\app\\cart.png", height, height);
		productInfo = new JTextPane();
        productInfo.setEditable(false);
        productInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        productInfo.setCharacterAttributes(attributes, true);

        attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
		
		JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(productImage);
        this.add(productInfo);
        this.add(orderState);
        this.add(addReview);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
	}
	
	/**
	 * @return the addReview
	 */
	public JButton getAddReview() {
		return addReview;
	}



	/**
	 * @param orderState the orderState to set
	 */
	public void setOrderState(String orderState) {
		this.orderState.setText(orderState);;
	}



	public void setOrderDetails(String listaProductos, double precio) {
	    productInfo.setContentType("text/html");
	    productInfo.setText(
	        "<html>" +
	        "<body style='font-family: SansSerif; font-size: 11px;'>" +
	            "<b>Products:</b> " + listaProductos + "<br><br>" +
	            "<span style='color: #5D4037; font-size: 13px;'><b>Total: " + String.format("%.2f", precio) + "€</b></span>" +
	        "</body>" +
	        "</html>"
	    );
	    productInfo.setPreferredSize(new Dimension(300, 80));
	}
    
	/**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        addReview.addActionListener(c);
    }
}
