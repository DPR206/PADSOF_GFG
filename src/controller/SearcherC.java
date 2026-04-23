package controller;

import java.awt.event.ActionEvent;
import model.product.Category;
import model.search.CategoryFilter;

import java.awt.event.ActionListener;
import java.util.*;

import model.store.Store;
import view.App;
import view.SearchPanel;
import view.SignupP;

public class SearcherC implements ActionListener{
	private final SearchPanel view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    
    public SearcherC(App frame) {
		this.view = new SearchPanel();
		this.frame = frame;
		this.model = Store.getInstance();
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Category> categories = new ArrayList<>();
		/*METEMOS LAS CATEGORÍAS PRIMERO*/
		if(e.getActionCommand().equals("Juegos de mesa")) {
			categories.add(model.getCategoryFromName("Juegos de mesa"));
		}
		if(e.getActionCommand().equals("Juegos de rol")) {
			categories.add(model.getCategoryFromName("Juegos de rol"));
		}
		if(e.getActionCommand().equals("Juegos de cartas")) {
			categories.add(model.getCategoryFromName("Juegos de cartas"));
		}
		if(e.getActionCommand().equals("Figuras")) {
			categories.add(model.getCategoryFromName("Figuras"));
		}
		if(e.getActionCommand().equals("Cómics")) {
			categories.add(model.getCategoryFromName("Cómics"));
		}
		if(categories.isEmpty() == false) {
			CategoryFilter c = new CategoryFilter(categories);
			frame.getUser().getSearcher().getStoreSearcher().addCategoryFilter(c);
		}
		
	}

}
