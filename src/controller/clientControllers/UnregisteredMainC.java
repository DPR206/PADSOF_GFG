package controller.clientControllers;

import controller.browserControllers.BrowseStoreC;
import model.store.Store;
import model.user.UnregisteredClient;
import view.App;
import view.browserPanels.BrowseStoreP;
import view.clientPanels.UnregisteredMainP;

import javax.swing.text.BadLocationException;

//import model.user.UnregisteredClient;
//import model.user.User;

public class UnregisteredMainC {
    private final UnregisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private UnregisteredClient user;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainC(App frame, Store model, UnregisteredClient user) {
        this.frame = frame;
        this.view = frame.getUnregisteredMainPanel();
        this.model = model;
        this.user = user;

        //new BannerUnregisteredC((BannerUnregistered) frame.getViewFromName("BANNER_UNREGISTERED"), this.frame);

        inicializar();
        linkControllers();
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
            this.view.getBrowsePanel().setVisible(true);
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

    public void linkControllers() {
        // Vinculamos los controladores hijos para que los paneles internos funcionen
        new BrowseStoreC(frame, model, view.getBrowsePanel());
        new SearcherC(frame, model, view.getFilterPanel());
    }
}