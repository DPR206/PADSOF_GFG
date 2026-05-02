package view;

import model.product.StoreProduct;
import model.store.Store;
import model.user.UnregisteredClient;
import view.browserPanels.BrowseStoreP;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class UnregisteredMainP extends JPanel {
    private static final long serialVersionUID = 1L;
	private JPanel banner;
    private JButton filters = new JButton("Filters");
    private JButton search = new JButton("Search");
    private JScrollPane scrolling;
    private SearchPanel filterP = new SearchPanel();
    private BrowseStoreP searching;
    private JPanel products;
    private JPanel productSearch;
    private List<StoreProduct> p;
    private UnregisteredClient mainU;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainP(UnregisteredClient mainU, App app) {
        super();
        this.mainU = mainU;
        this.banner = new JPanel();
        this.productSearch = new JPanel();
        this.productSearch.setLayout(new BorderLayout());

        this.filterP.setVisible(false);

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
        
        banner = new BannerUnregistered();
        add(banner, BorderLayout.NORTH);

        JPanel others = new JPanel(new BorderLayout());

        JPanel botones = new JPanel(new GridLayout(0, 2));
        botones.add(this.search);
        botones.add(this.filters);
        others.add(botones, BorderLayout.NORTH);

        this.add(others, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new CardLayout());
        bottom.add(this.searching, "Search");
        bottom.add(this.filterP, "Search");
        others.add(bottom, BorderLayout.SOUTH);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public BrowseStoreP getBrowsePanel() {
        return this.searching;
    }

    public SearchPanel getFilterPanel() {
        return this.filterP;
    }

    public void setController(ActionListener e) {
        this.filters.addActionListener(e);
        this.search.addActionListener(e);

    }
}