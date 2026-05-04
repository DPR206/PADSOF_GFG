package view.miniPanels;

import static main.Main.brownColour;
import static view.ImageAdder.getImageLabel;
import static view.ImageAdder.getScaledImage;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import model.product.Pack;

public class PackMiniEdit extends JPanel{
	private final JButton gestionar = new JButton("Gestionar");
    private final Pack p;
    private final JTextPane packInfo;
    private final JLabel packImage;
    
    public PackMiniEdit(Pack p, int index) throws BadLocationException {
    	super();
    	
    	this.p = p;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());
        
        gestionar.setPreferredSize(new Dimension(125, height));
        gestionar.setIcon(getScaledImage(new ImageIcon(".\\resources\\cart.png"), height / 4, height / 4));
    
        this.packImage = getImageLabel(p.getPhoto(), height, width);
        this.packInfo = new JTextPane();
        this.packInfo.setEditable(false);
        this.packInfo.setFocusable(false);
        
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        packInfo.setCharacterAttributes(attributes, true);
        packInfo.setText(p.getId() + "\n");
        
        attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);

        Document doc = packInfo.getStyledDocument();
        doc.insertString(doc.getLength(), // DUE Añadir descuento
                ("Price: " + String.format("%.2f", this.p.getPrice()) + " €\n"), attributes);

        packInfo.setPreferredSize(new Dimension(width, height));

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));
        
        this.add(indexNum);
        this.add(packImage);
        this.add(packInfo);
        this.add(gestionar);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }
    
    public JLabel getPackImage() {
        return packImage;
    }

    public JTextPane getPackInfo() {
        return packInfo;
    }

    public Pack getPack() {
        return p;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        gestionar.addActionListener(c);
    }
}

