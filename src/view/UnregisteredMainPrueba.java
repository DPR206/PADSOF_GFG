package view;

import model.product.StoreProduct;
import model.user.UnregisteredClient;
import view.browserPanels.BrowseStoreP;
import view.clientPanels.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class UnregisteredMainPrueba extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton filters = new JButton("Filters");
    private JButton search = new JButton("Search");
    private SearchPanel filterP;
    private BrowseStoreP searchingP;
    private JPanel bottom;
    private JPanel others;
    private CardLayout cardLayout = new CardLayout();
    private JPanel products;
    private JPanel productSearch;
    private List<StoreProduct> p;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainPrueba() {

        /*this.mainU = mainU;
        this.productSearch = new JPanel();
        this.productSearch.setLayout(new BorderLayout());
        this.app = app;*/

        //this.filterP.setVisible(false);

        configurarEstructura();
        //repaintALL();
    }

    private void configurarEstructura() {
    	this.setLayout(new BorderLayout());

    	others = new JPanel(new BorderLayout());

    	JPanel botones = new JPanel(new GridLayout(0, 2));
    	botones.add(this.search);
        botones.add(this.filters);
        //this.add(botones, BorderLayout.NORTH);

        others.add(botones, BorderLayout.SOUTH);

        bottom = new JPanel(cardLayout);

        others.add(bottom, BorderLayout.SOUTH);
	}

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    public BrowseStoreP getBrowsePanel() {
        return this.searchingP;
    }

    public SearchPanel getFilterPanel() {
        return this.filterP;
    }

    public void setController(ActionListener e) {
        this.filters.addActionListener(e);
        this.search.addActionListener(e);

    }

    /**
	 * @return the filterP
	 */
	public SearchPanel getFilterP() {
		return filterP;
	}

	/**
	 * @param filterP the filterP to set
	 */
	public void setFilterP(SearchPanel filterP) {
		this.filterP = filterP;
	}

	/**
	 * @return the searchingP
	 */
	public BrowseStoreP getSearchingP() {
		return searchingP;
	}

	/**
	 * @param searchingP the searchingP to set
	 */
	public void setSearchingP(BrowseStoreP searchingP) {
		this.searchingP = searchingP;
	}

	/**
	 * @return the filters
	 */
	public JButton getFilters() {
		return filters;
	}

	/**
	 * @return the search
	 */
	public JButton getSearch() {
		return search;
	}

	/**
	 * @return the p
	 */
	public List<StoreProduct> getP() {
		return p;
	}

	/**
	 * @param p the p to set
	 */
	public void setP(List<StoreProduct> p) {
		this.p = p;
	}

	public void setPanelInferior(JPanel panel, String nombre) {
        // El controlador decide qué panel inyectar aquí
        this.bottom.add(panel, nombre);
        this.cardLayout.show(bottom, nombre);

        this.revalidate();
        this.repaint();
    }

}