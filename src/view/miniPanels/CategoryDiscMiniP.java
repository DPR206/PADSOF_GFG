package view.miniPanels;

import model.product.Category;
import view.ImageAdder;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static main.Main.brownColour;
import static view.ImageAdder.getScaledImage;

/**
 * The type Category disc mini p.
 * @author Ana O.R.
 * @version 1.0
 */
public class CategoryDiscMiniP extends AbstractMiniP {
    private final JButton button;
    private final Category category;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Category disc mini p.
     * @param category      the category
     * @param index         the index
     * @param alreadyChosen the already chosen
     */
    public CategoryDiscMiniP(Category category, int index, List<Category> alreadyChosen) {
        super();

        this.category = category;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        button = new JButton("Add to discount");
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(("Add to discount").length() * 15, height));
        button.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\add.png"), height / 3, height / 3));
        if (alreadyChosen.contains(category)) {
            button.setEnabled(false);
        }

        JLabel categoryImage = ImageAdder.getImageLabel(".\\resources\\app\\check.png", 50, 50);
        JTextPane categoryInfo = new JTextPane();
        categoryInfo.setEditable(false);
        categoryInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        categoryInfo.setCharacterAttributes(attributes, true);
        categoryInfo.setText(category.getName() + "\n");

        categoryInfo.setPreferredSize(new Dimension(width + 16, height));

        JTextPane indexNum = new JTextPane();
        indexNum.setEditable(false);
        indexNum.setFocusable(false);

        SimpleAttributeSet attributes2 = new SimpleAttributeSet();
        StyleConstants.setBold(attributes2, true);
        indexNum.setCharacterAttributes(attributes2, true);
        indexNum.setText("\n" + index + ".");
        indexNum.setPreferredSize(new Dimension(25, height));

        this.add(indexNum);
        this.add(categoryImage);
        this.add(categoryInfo);
        this.add(button);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * Gets button.
     * @return the button
     */
    public JButton getButton() {
        return button;
    }

    /**
     * It gets the category
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}