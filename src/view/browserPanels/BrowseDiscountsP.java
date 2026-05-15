package view.browserPanels;

import model.discount.Discount;
import view.miniPanels.DiscountMiniP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.Serial;

import static main.Main.brownColour;

public class BrowseDiscountsP extends AbstractBrowserP<Discount> {
    @Serial
    private static final long serialVersionUID = 1L;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    public BrowseDiscountsP() throws BadLocationException {
        super();
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
    public void addMiniPanel(Discount item, int index) throws BadLocationException {
        DiscountMiniP miniOrder = new DiscountMiniP(item, index, "Add to discount");
        super.addMiniPanel(miniOrder);
        this.add(miniOrder);
    }
}