package view.employeePanels;

import view.App;
import view.browserPanels.BrowseExchangesP;
import view.browserPanels.BrowseSecondHandProductsP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

/**
 * The type Employee exchange perm p.
 * @author Ana O.R.
 * @version 1.0
 */
public class EmployeeExchangePermP extends JPanel {
    private final JButton browseExchanges = new JButton("Browse Exchanges");
    private final JButton valuateProducts = new JButton("Valuate Products");
    private final CardLayout cardLayout = new CardLayout();
    private final BrowseExchangesP browseExchangesP = new BrowseExchangesP();
    private final BrowseSecondHandProductsP browseSecondHandProductsP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Employee exchange perm p.
     * @throws BadLocationException the bad location exception
     */
    public EmployeeExchangePermP(App frame) throws BadLocationException {
        this.setLayout(new BorderLayout());
        browseSecondHandProductsP = new BrowseSecondHandProductsP(frame, "Add to Offer", ".\\resources\\app\\add.png");

        paintEverything();
    }

    /**
     * Paint everything.
     */
    public void paintEverything() {
        this.removeAll();

        JPanel options = new JPanel(new FlowLayout());
        options.add(browseExchanges);
        options.add(valuateProducts);
        this.add(options, BorderLayout.NORTH);

        JPanel cards = new JPanel(cardLayout);
        cards.add(browseExchangesP, "BROWSE_EXCHANGES");
        cards.add(browseSecondHandProductsP, "BROWSE_SECOND_HAND_PRODUCTS");
        this.add(cards, BorderLayout.CENTER);

        this.revalidate();
        this.repaint();
    }

    public JButton getBrowseExchanges() {
        return browseExchanges;
    }

    public JButton getValuateProducts() {
        return valuateProducts;
    }

}