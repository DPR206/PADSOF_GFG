package controller;

import controller.browserControllers.BrowseStoreC;
import model.product.StoreProduct;
import model.store.Store;
import model.user.*;
import view.App;
import view.SearchPanel;
import view.UnregisteredMainP;
import view.browserPanels.BrowseStoreP;

import javax.swing.text.BadLocationException;
import java.awt.event.*;
import java.util.List;

public class UnregisteredMainPruebaC implements ActionListener {
    private final UnregisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private UnregisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainPruebaC(App frame, Store model, UnregisteredClient user) {
        this.frame = frame;
        this.view = frame.getUnregisteredMainPanel();
        this.model = model;
        this.user = user;

        inicializar();
        updateControllers();
    }

    private void inicializar() {
    	List<StoreProduct> productos = Store.getInstance().getStoreProductList();
		
    	view.getSearch().addActionListener(e -> {
    		try {
                this.view.setSearchingP(new BrowseStoreP(frame));
                this.view.getSearchingP().setVisible(false);
            } catch (BadLocationException exc) {
                exc.printStackTrace();
            }
    		
    		this.view.setP(this.user.searchStoreProduct());
    		this.view.setPanelInferior(this.view.getBrowsePanel(), "Search");
    		
    	});
    	
    	view.getFilters().addActionListener(e -> {
    		this.view.setFilterP(new SearchPanel());
    		this.view.setPanelInferior(view.getFilterP(), "Filters");
    	});
	}

	public void updateControllers() {
        view.getBrowsePanel().setController(new BrowseStoreC(frame, model, view.getBrowsePanel()));
        view.getFilterPanel().setController(new SearcherC(frame, model, view.getFilterPanel()));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Browse Wallet")) {
            view.setVisible(false);
        }
        if (e.getActionCommand().equals("Filters")) {
            view.getBrowsePanel().setVisible(false);
            view.getFilterPanel().setVisible(true);
        } else if (e.getActionCommand().equals("Search")) {
            view.getFilterPanel().setVisible(false);
            view.getBrowsePanel().setVisible(true);
            try {
                view.getBrowsePanel().setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                throw new RuntimeException(ex);
            }
        }
        updateControllers();
    }
}
