package view.miniPanels;

import model.product.Category;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import static main.Main.brownColour;
import static view.ImageAdder.getScaledImage;

public class CategoryDiscMiniP extends AbstractMiniP {
    private final JButton button;
    private final Category category;
    private final JTextPane categoryInfo;
    //private final JPanel categoryImage;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public CategoryDiscMiniP(Category category, int index, List<Category> alreadyChosen) throws BadLocationException {
        super();

        this.category = category;
        int width = 350;
        int height = 60;
        this.setLayout(new FlowLayout());

        button = new JButton("Add to discount");
        button.setPreferredSize(new Dimension(("Add to discount").length() * 15, height));
        button.setIcon(getScaledImage(new ImageIcon(".\\resources\\app\\add.png"), height / 3, height / 3));
        if (alreadyChosen.contains(category)) {
            button.setEnabled(false);
        }

        //this.categoryImage = getCategoryImagePanel(p, height, height);
        this.categoryInfo = new JTextPane();
        this.categoryInfo.setEditable(false);
        this.categoryInfo.setFocusable(false);

        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        StyleConstants.setBold(attributes, true);
        categoryInfo.setCharacterAttributes(attributes, true);
        categoryInfo.setText("Category: " + category.getName() + "\n");

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
        //this.add(categoryImage);
        this.add(categoryInfo);
        this.add(button);

        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));
    }

    /**
     * Instantiates a new Category mini p.
     * @param p     the p
     * @param index the index
     * @throws BadLocationException the bad location exception
     */

    /**
     * Gets button.
     * @return the button
     */
    public JButton getButton() {
        return button;
    }

    public Category getCategory() {
        return category;
    }

    public JTextPane getCategoryInfo() {
        return categoryInfo;
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        button.addActionListener(c);
    }
}