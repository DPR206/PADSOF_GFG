package controller;

import controller.browserControllers.BrowseStoreC;
import controller.clientControllers.*;

import model.store.Store;
import model.user.UnregisteredClient;
import view.App;

import view.UnregisteredMainPrueba;

import view.browserPanels.BrowseStoreP;

import javax.swing.text.BadLocationException;


public class UnregisteredMainPruebaC {
    private final UnregisteredMainPrueba view; /* view -> panel */
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
        vincularSubControladores();
    }

    private void inicializar() {

    	//List<StoreProduct> productos = Store.getInstance().getStoreProductList();
		
    	view.getSearch().addActionListener(e -> {
    		try {
                this.view.setSearchingP(new BrowseStoreP(frame));
                this.view.getSearchingP().setVisible(false);

            } catch (BadLocationException exc) {
                exc.printStackTrace();
            }
    		
    		this.view.setP(this.user.searchStoreProduct());
    		this.view.setPanelInferior(this.view.getBrowsePanel(), "Search");
    		try {
                view.getBrowsePanel().setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
    		
    	});
    	
    	view.getFilters().addActionListener(e -> {
    		//this.view.setFilterP(new SearchPanel());
    		this.view.setPanelInferior(view.getFilterP(), "Filters");
    	});
	}

    private void vincularSubControladores() {
        // Vinculamos los controladores hijos para que los paneles internos funcionen
        new BrowseStoreC(frame, model, view.getBrowsePanel());
        new SearcherC(frame, model, view.getFilterPanel());
    }

    public void updateControllers() {
        view.getBrowsePanel().setController(new BrowseStoreC(frame, model, view.getBrowsePanel()));
        view.getFilterPanel().setController(new SearcherC(frame, model, view.getFilterPanel()));

    }

}