package controller.clientControllers;

import controller.browserControllers.BrowseStoreC;
import model.store.Store;
import model.user.UnregisteredClient;
import view.App;
import view.clientPanels.UnregisteredMainP;

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

        inicializar();
        linkControllers();
    }

    private void inicializar() {

        //List<StoreProduct> productos = Store.getInstance().getStoreProductList();
        //this.view.setP(productos);

        view.getSearch().addActionListener(e -> {

            this.view.setP(this.user.searchStoreProduct());
            this.view.setPanelInferior(this.view.getBrowsePanel(), "Search");
            /*try {
                view.getBrowsePanel().setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }*/

        });

        view.getFilters().addActionListener(e -> {
            this.view.setPanelInferior(view.getFilterP(), "Filters");
        });
    }

    public void linkControllers() {
        new BrowseStoreC(frame, model, view.getBrowsePanel());
        new SearcherC(frame, model, view.getFilterPanel());
    }
}