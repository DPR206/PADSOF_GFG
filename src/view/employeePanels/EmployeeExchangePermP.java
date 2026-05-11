package view.employeePanels;

import view.App;
import view.browserPanels.BrowseExchangesP;
import view.browserPanels.BrowseSecondHandProductsP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class EmployeeExchangePermP extends JPanel {
    private final App app;
    private final JButton browseExchanges = new JButton("Browse Exchanges");
    private final JButton valuateProducts = new JButton("Valuate Products");
    private final CardLayout cardLayout = new CardLayout();
    private final BrowseExchangesP browseExchangesP = new BrowseExchangesP();
    private final BrowseSecondHandProductsP browseSecondHandProductsP =
            new BrowseSecondHandProductsP("Add to Offer", null);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public EmployeeExchangePermP(App app) throws BadLocationException {
        this.setLayout(new BorderLayout());

        this.app = app;

        paintEverything();
    }

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

}