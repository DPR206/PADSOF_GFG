package controller.clientControllers;

import controller.Controller;
import model.product.Category;
import model.search.CategoryFilter;
import model.store.Store;
import view.App;
import view.clientPanels.SearchPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Searcher c.
 * @author Sofía C.L.
 * @version 1.0
 */
public class SearcherC implements Controller {
    private final SearchPanel view;
    private final App frame;
    private final Store model;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Searcher c.
     * @param frame the frame
     * @param model the model
     * @param view  the view
     */
    public SearcherC(App frame, Store model, SearchPanel view) {
        this.view = view;
        this.frame = frame;
        this.model = model;

        initializeActions();
    }

    @Override
    public void initializeActions() {
        List<Category> categories = new ArrayList<>();
        this.frame.getUser().getSearcher().getStoreSearcher().clearFilters();

        view.getAplicar().addActionListener(e -> {
            /*METEMOS LAS CATEGORÍAS PRIMERO*/
            if (view.getJmesa().isSelected()) {
                categories.add(model.getCategoryFromName("Juegos de mesa"));
            }
            if (view.getJrol().isSelected()) {
                categories.add(model.getCategoryFromName("Juegos de rol"));
            }
            if (view.getJcarta().isSelected()) {
                categories.add(model.getCategoryFromName("Juegos de cartas"));
            }
            if (view.getFiguras().isSelected()) {
                categories.add(model.getCategoryFromName("Figuras"));
            }
            if (view.getComics().isSelected()) {
                categories.add(model.getCategoryFromName("Cómics"));
            }
            if (!categories.isEmpty()) {
                CategoryFilter c = new CategoryFilter(categories);
                frame.getUser().getSearcher().getStoreSearcher().addCategoryFilter(c);
            }

            /*Ahora metemos los filtros de puntuación*/

            if (view.getCerouno().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(0, 1);
            }
            if (view.getUnodos().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(1, 2);
            }
            if (view.getDostres().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(2, 3);
            }
            if (view.getTrescuatro().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(3, 4);
            }
            if (view.getCuatrocinco().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4, 5);
            }


            /*Ahora metemos el filtro de los precios*/

            if (view.getCerodiez().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(0, 10);
            }
            if (view.getDiezquince().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(10, 15);
            }
            if (view.getQuinceveinte().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(15, 20);
            }
            if (view.getVeintetreinta().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(20, 30);
            }
            if (view.getTreintacuarenta().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(30, 40);
            }
            if (view.getCuarentacincuenta().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(40, 50);
            }
            if (view.getPlus50().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(50, Double.MAX_VALUE);
            }

            if (view.getAscendente().isSelected()) {
                frame.getUser().getSearcher().getStoreSearcher().setAsc(true);
            } else {
                frame.getUser().getSearcher().getStoreSearcher().setAsc(false);
            }
        });
    }
}