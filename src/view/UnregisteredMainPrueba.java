package view;

import model.product.StoreProduct;
import view.browserPanels.BrowseStoreP;
import view.clientPanels.SearchPanel;

import javax.swing.*;

import controller.UnregisteredMainPruebaC;

import java.awt.*;
import java.util.List;

public class UnregisteredMainPrueba extends JPanel {
    private static final long serialVersionUID = 1L;
    private JButton filters = new JButton("Filters");
    private JButton search = new JButton("Search");
    private SearchPanel filterP = new SearchPanel();
    private BrowseStoreP searchingP;
    private JPanel bottom;
    private JPanel others;
    private CardLayout cardLayout = new CardLayout();
    private List<StoreProduct> p;
    //private UnregisteredMainPruebaC controller;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainPrueba() {

        configurarEstructura();
    }

    private void configurarEstructura() {
    	this.setLayout(new BorderLayout());
    	
    	others = new JPanel(new BorderLayout());
    	
    	JPanel botones = new JPanel(new GridLayout(0, 2));
    	botones.add(this.search);
        botones.add(this.filters);
        //this.add(botones, BorderLayout.NORTH);
        
        others.add(botones, BorderLayout.NORTH);
        
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

    /*public void setController(UnregisteredMainPruebaC controller) {
        this.controller = controller;
    }*/
    
    /*
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
        this.bottom.add(panel, nombre);
        this.cardLayout.show(bottom, nombre);
        
        this.revalidate();
        this.repaint();
    }
  
}
