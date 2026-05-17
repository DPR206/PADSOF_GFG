package view.browserPanels;

import model.product.Category;
import view.miniPanels.CategoryDiscMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.Serial;
import java.util.List;

import static main.Main.brownColour;

/**
 * The type Browse categories disc p.
 * @author Ana O.R.
 * @version 1.0
 */
public class BrowseCategoriesDiscP extends AbstractBrowserP<Category> {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Category> alreadyChosen;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Browse categories disc p.
     * @param alreadyChosen the already chosen
     * @throws BadLocationException the bad location exception
     */
    public BrowseCategoriesDiscP(List<Category> alreadyChosen) throws BadLocationException {
        super();
        this.alreadyChosen = alreadyChosen;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        paintEverything();
    }

    @Override
    public void paintEverything() throws BadLocationException {
        this.removeAll();

        super.addAllMiniPanels();
        this.add(super.getPageTurner());
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, brownColour));

        this.revalidate();
        this.repaint();
    }

    @Override
    public void addMiniPanel(Category item, int index) throws BadLocationException {
        CategoryDiscMiniP miniOrder = new CategoryDiscMiniP(item, index, alreadyChosen);
        super.addMiniPanel(miniOrder);
        this.add(miniOrder);

    }
}