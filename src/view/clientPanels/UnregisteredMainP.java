package view.clientPanels;

import model.product.StoreProduct;
import model.user.UnregisteredClient;
import view.App;
import view.browserPanels.BrowseStoreP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class UnregisteredMainP extends JPanel {
    private static final long serialVersionUID = 1L;
    JPanel bottom;
    private JButton filters = new JButton("Filters");
    private JButton search = new JButton("Search");
    private SearchPanel filterP = new SearchPanel();
    private BrowseStoreP searchingP;
    private JPanel productSearch;
    private List<StoreProduct> p;
    private App app;
    private JPanel others;
    private CardLayout cardLayout = new CardLayout();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainP(UnregisteredClient mainU, App app) throws BadLocationException {
        searchingP = new BrowseStoreP(app);

        configurarEstructura();
    }

    private void configurarEstructura() {
        this.setLayout(new BorderLayout());

        others = new JPanel(new BorderLayout());

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.search);
        botones.add(this.filters);

        others.add(botones, BorderLayout.NORTH);

        bottom = new JPanel(cardLayout);

        others.add(bottom, BorderLayout.SOUTH);

        this.add(others, BorderLayout.CENTER);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public App getApp() {
        return app;
    }

    public void setApp(App newApp) {
        this.app = newApp;
    }

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

    public JPanel getOthers() {
        return others;
    }

    public void setOthers(JPanel newOthers) {
        this.others = newOthers;
    }

    public List<StoreProduct> getP() {
        return p;
    }

    public void setP(List<StoreProduct> newP) {
        this.p = newP;
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

    public void setController(ActionListener e) {
        this.filters.addActionListener(e);
        this.search.addActionListener(e);

    }

    public void setPanelInferior(JPanel panel, String nombre) {
        this.bottom.add(panel, nombre);
        this.cardLayout.show(bottom, nombre);

        this.revalidate();
        this.repaint();
    }
}