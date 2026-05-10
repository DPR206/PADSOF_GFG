package controller.clientControllers;

import controller.browserControllers.BrowseStoreC;
import model.product.StoreProduct;
import model.search.PriceFilter;
import model.store.Store;
import model.user.UnregisteredClient;
import view.App;
import view.clientPanels.UnregisteredMainP;

import javax.swing.text.BadLocationException;
import java.util.List;

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
        this.user = (UnregisteredClient) frame.getUser();

        inicializar();
        linkControllers();
    }

    private void inicializar() {
        List<StoreProduct> productos = Store.getInstance().getStoreProductList();
        this.view.getBrowsePanel().setFirstItemList(model.getPacks());
        this.view.getBrowsePanel().setSecondItemList(model.getStoreProductList());
        try {
            view.getBrowsePanel().paintEverything();
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
        this.view.setPanelInferior(this.view.getBrowsePanel(), "Search");

        view.getSearch().addActionListener(e -> {
            System.out.println("FILTERS:");
            for (PriceFilter pf : frame.getUser().getSearcher().getStoreSearcher().getPriceF()) {
                System.out.println(pf.toString());
            }
            System.out.println(this.user.searchStoreProduct()); // FALLA: user.searchStoreProduct();
            this.view.getBrowsePanel().setSecondItemList(this.user.searchStoreProduct());
            try {
                view.getBrowsePanel().setCurrentPageNum(1);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
            this.view.setPanelInferior(this.view.getBrowsePanel(), "Search");

        });

        view.getFilters().addActionListener(e -> {
            this.view.setPanelInferior(view.getFilterP(), "Filters");
        });
    }

    public void linkControllers() {
        new BrowseStoreC(frame, model, view.getBrowsePanel());
//        new SearcherC(frame, model, view.getFilterPanel());
        this.view.getFilterPanel().setController(new SearcherC(frame, model, view.getFilterPanel()));
    }
}