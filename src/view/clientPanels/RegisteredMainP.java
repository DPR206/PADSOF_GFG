package view.clientPanels;

import view.browserPanels.BrowseStoreProductsP;
import view.browserPanels.MixedBrowseStoreAddToCartP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.io.Serial;

/**
 * The type Registered main p.
 * @author Duna P.R.
 * @version 1.0
 */
public class RegisteredMainP extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton secondHand = new JButton("Second Hand");
    private final SearchPanel filterP = new SearchPanel();
    private final RegisteredMakeOfferP makeOfferP = new RegisteredMakeOfferP();
    private final CardLayout cardLayout = new CardLayout();
    private JPanel bottom;
    private JButton filters = new JButton("Filters");
    private JButton search = new JButton("Search");
    private MixedBrowseStoreAddToCartP searchingP;
    private BrowseStoreProductsP recommendedProductsPanel;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Registered main p.
     * @throws BadLocationException the bad location exception
     */
    public RegisteredMainP() throws BadLocationException {
        searchingP = new MixedBrowseStoreAddToCartP();

        configurarEstructura();
    }

    private void configurarEstructura() throws BadLocationException {
        this.setLayout(new BorderLayout());

        JPanel others = new JPanel(new BorderLayout());
        others.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel botones = new JPanel(new GridLayout(0, 3));
        botones.add(this.search);
        botones.add(this.filters);
        botones.add(this.secondHand);

        others.add(botones, BorderLayout.NORTH);

        JPanel searchAndRecommend = new JPanel(new GridLayout(1, 2));
        searchAndRecommend.add(searchingP);

        recommendedProductsPanel = new BrowseStoreProductsP("Add to Cart");
        JPanel recommendAndTitle = new JPanel();
        recommendAndTitle.setLayout(new BoxLayout(recommendAndTitle, BoxLayout.Y_AXIS));
        JLabel title = new JLabel("We think you may like...");
        title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, 18));
        recommendAndTitle.add(title);
        recommendAndTitle.add(recommendedProductsPanel);
        searchAndRecommend.add(recommendAndTitle);

        bottom = new JPanel(cardLayout);
        bottom.setOpaque(false);
        bottom.add(searchAndRecommend, "Search");
        bottom.add(filterP, "Filters");
        bottom.add(makeOfferP, "Second Hand");

        others.add(bottom, BorderLayout.CENTER);

        this.add(others, BorderLayout.CENTER);
    }

    /**
     * It gets the bottom
     * @return the bottom
     */
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
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
     * It gets the make offer p
     * @return the make offer p
     */
    public RegisteredMakeOfferP getMakeOfferP() {
        return makeOfferP;
    }

    /**
     * It gets the recommended products panel
     * @return the recommended products panel
     */
    public BrowseStoreProductsP getRecommendedProductsPanel() {
        return recommendedProductsPanel;
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
     * It gets the second hand
     * @return the second hand
     */
    public JButton getSecondHand() {
        return secondHand;
    }

}