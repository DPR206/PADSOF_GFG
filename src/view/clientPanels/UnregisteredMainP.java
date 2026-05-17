package view.clientPanels;

import view.browserPanels.MixedBrowseStoreAddToCartP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serial;

/**
 * The type Unregistered main p.
 * @author Ana O.R.
 * @version 1.0
 */
public class UnregisteredMainP extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final SearchPanel filterP = new SearchPanel();
    private final CardLayout cardLayout = new CardLayout();
    private JPanel bottom;
    private JButton filters = new JButton("Filters");
    private JButton search = new JButton("Search");
    private MixedBrowseStoreAddToCartP searchingP;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Unregistered main p.
     * @throws BadLocationException the bad location exception
     */
    public UnregisteredMainP() throws BadLocationException {
        searchingP = new MixedBrowseStoreAddToCartP();

        paintEverything();
    }

    /**
     * Paint everything.
     */
    public void paintEverything() {
        this.setLayout(new BorderLayout());

        JPanel others = new JPanel(new BorderLayout());
        others.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.search);
        botones.add(this.filters);

        others.add(botones, BorderLayout.NORTH);

        bottom = new JPanel(cardLayout);
        bottom.setOpaque(false);
        bottom.add(searchingP, "Search");
        bottom.add(filterP, "Filters");

        others.add(bottom, BorderLayout.CENTER);

        this.add(others, BorderLayout.CENTER);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * It gets the bottom
     * @return the bottom
     */
    public JPanel getBottom() {
        return bottom;
    }

    /**
     * It gets the browse panel
     * @return the browse panel
     */
    public MixedBrowseStoreAddToCartP getBrowsePanel() {
        return this.searchingP;
    }

    /**
     * It gets the card layout
     * @return the card layout
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * It gets the filter panel
     * @return the filter panel
     */
    public SearchPanel getFilterPanel() {
        return this.filterP;
    }

    /**
     * It gets the filters
     * @return the filters
     */
    public JButton getFilters() {
        return filters;
    }

    /**
     * It sets the filters
     * @param newFilters the new filters
     */
    public void setFilters(JButton newFilters) {
        this.filters = newFilters;
    }

    /**
     * It gets the search
     * @return the search
     */
    public JButton getSearch() {
        return search;
    }

    /**
     * It sets the search
     * @param newSearch the new search
     */
    public void setSearch(JButton newSearch) {
        this.search = newSearch;
    }

    /**
     * It gets the searching
     * @return the searching
     */
    public MixedBrowseStoreAddToCartP getSearching() {
        return searchingP;
    }

    /**
     * It sets the searching
     * @param newSearching the new searching
     */
    public void setSearching(MixedBrowseStoreAddToCartP newSearching) {
        this.searchingP = newSearching;
    }

    /**
     * It sets the controller
     * @param e the e
     */
    public void setController(ActionListener e) {
        this.filters.addActionListener(e);
        this.search.addActionListener(e);

    }
}