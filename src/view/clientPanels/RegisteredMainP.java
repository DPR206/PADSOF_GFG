package view.clientPanels;

import view.browserPanels.BrowseStoreP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class RegisteredMainP extends JPanel {
    private static final long serialVersionUID = 1L;
    JPanel bottom;
    private JButton filters = new JButton("Filters");
    private JButton search = new JButton("Search");
    private JButton secondHand = new JButton("Second Hand");
    private SearchPanel filterP = new SearchPanel();
    private BrowseStoreP searchingP;
    private RegisteredMakeOfferP makeOfferP = new RegisteredMakeOfferP();
    private JPanel productSearch;
    private JPanel others;
    private CardLayout cardLayout = new CardLayout();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public RegisteredMainP() throws BadLocationException {
        searchingP = new BrowseStoreP();

        configurarEstructura();
    }

    private void configurarEstructura() {
        this.setLayout(new BorderLayout());

        others = new JPanel(new BorderLayout());
        others.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.search);
        botones.add(this.filters);
        botones.add(this.secondHand);

        others.add(botones, BorderLayout.NORTH);

        bottom = new JPanel(cardLayout);
        bottom.setOpaque(false);
        bottom.add(searchingP, "Search");
        bottom.add(filterP, "Filters");
        bottom.add(makeOfferP, "Second Hand");

        others.add(bottom, BorderLayout.CENTER);

        this.add(others, BorderLayout.CENTER);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    public JPanel getBottom() {
        return bottom;
    }

    public void setBottom(JPanel newBottom) {
        this.bottom = newBottom;
    }

    public BrowseStoreP getBrowsePanel() {
        return this.searchingP;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void setCardLayout(CardLayout newCardLayout) {
        this.cardLayout = newCardLayout;
    }

    public SearchPanel getFilterP() {
        return filterP;
    }

    public void setFilterP(SearchPanel newFilterP) {
        this.filterP = newFilterP;
    }

    public SearchPanel getFilterPanel() {
        return this.filterP;
    }

    public JButton getFilters() {
        return filters;
    }

    public void setFilters(JButton newFilters) {
        this.filters = newFilters;
    }

    public RegisteredMakeOfferP getMakeOfferP() {
        return makeOfferP;
    }

    public void setMakeOfferP(RegisteredMakeOfferP newMakeOfferP) {
        this.makeOfferP = newMakeOfferP;
    }

    public JPanel getOthers() {
        return others;
    }

    public void setOthers(JPanel newOthers) {
        this.others = newOthers;
    }

    public JPanel getProductSearch() {
        return productSearch;
    }

    public void setProductSearch(JPanel newProductSearch) {
        this.productSearch = newProductSearch;
    }

    public JButton getSearch() {
        return search;
    }

    public void setSearch(JButton newSearch) {
        this.search = newSearch;
    }

    public BrowseStoreP getSearching() {
        return searchingP;
    }

    public void setSearching(BrowseStoreP newSearching) {
        this.searchingP = newSearching;
    }

    public BrowseStoreP getSearchingP() {
        return searchingP;
    }

    public void setSearchingP(BrowseStoreP newSearchingP) {
        this.searchingP = newSearchingP;
    }

    public JButton getSecondHand() {
        return secondHand;
    }

    public void setSecondHand(JButton newSecondHand) {
        this.secondHand = newSecondHand;
    }

    public void setPanelInferior(JPanel panel, String nombre) {
        this.bottom.add(panel, nombre);
        this.cardLayout.show(bottom, nombre);

        this.revalidate();
        this.repaint();
    }
}