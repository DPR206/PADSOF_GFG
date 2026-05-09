package view.clientPanels;

import model.product.StoreProduct;
import model.store.Store;
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
    private BrowseStoreP searching;
    private JPanel productSearch;
    private List<StoreProduct> p;
    private UnregisteredClient mainU;
    private App app;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainP(UnregisteredClient mainU, App app) {
        super();
        this.mainU = mainU;
        this.productSearch = new JPanel();
        this.productSearch.setLayout(new BorderLayout());
        this.app = app;

        this.filterP.setVisible(false);

        repaintALL();
    }

    public void repaintALL() {
        this.removeAll();

        List<StoreProduct> products = Store.getInstance().getStoreProductList();
        this.p = products;
        try {
            this.searching = new BrowseStoreP(app);
            this.searching.setVisible(false);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        Store s = Store.getInstance();
        this.p = this.mainU.searchStoreProduct();

        this.setLayout(new BorderLayout());

        JPanel others = new JPanel(new BorderLayout());

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.search);
        botones.add(this.filters);
        others.add(botones, BorderLayout.NORTH);

        this.add(others, BorderLayout.CENTER);

        bottom = new JPanel(new CardLayout());
        bottom.add(this.searching, "Search");
        bottom.add(this.filterP, "Filters");
        others.add(bottom, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public App getApp() {
        return app;
    }

    public void setApp(App newApp) {
        this.app = newApp;
    }

    public BrowseStoreP getBrowsePanel() {
        return this.searching;
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

    public UnregisteredClient getMainU() {
        return mainU;
    }

    public void setMainU(UnregisteredClient newMainU) {
        this.mainU = newMainU;
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
        return searching;
    }

    public void setSearching(BrowseStoreP newSearching) {
        this.searching = newSearching;
    }

    public void setController(ActionListener e) {
        this.filters.addActionListener(e);
        this.search.addActionListener(e);

    }

    public void setPanelInferior(JPanel panel, String constraints) {
        bottom.add(panel, constraints);
    }
}