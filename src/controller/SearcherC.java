package controller;


import java.awt.event.ActionEvent;
import model.product.Category;
import model.search.CategoryFilter;
import model.search.PunctuationFilter;

import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.text.BadLocationException;

import model.store.Store;
import model.user.UnregisteredClient;
import view.App;
import view.SearchPanel;
import view.SignupP;

public class SearcherC implements ActionListener{
	private final SearchPanel view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */

    public SearcherC(App frame, SearchPanel view) {
		this.view = view;
		this.frame = frame;
		this.model = Store.getInstance();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Category> categories = new ArrayList<>();

		if(e.getActionCommand().equals("Aplicar filtros")) {
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

			/*Ahora metemos los filtros de puntuación*/


			if(e.getActionCommand().equals("0-1★")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(0, 1);
			}
			if(e.getActionCommand().equals("1-2★")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(1,2);
			}
			if(e.getActionCommand().equals("2-3★")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(2,3);
			}
			if(e.getActionCommand().equals("3-4★")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(3,4);
			}
			if(e.getActionCommand().equals("4-5★")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}


			/*Ahora metemos el filtro de los precios*/

			/*if(e.getActionCommand().equals("0-10")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(0, 1);
			} Transformado en lo de debajo */
			if(view.getCerodiez().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(0, 1);
			}
			if(e.getActionCommand().equals("10-15")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(1,2);
			}
			if(e.getActionCommand().equals("15-20")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(2,3);
			}
			if(e.getActionCommand().equals("20-30")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(3,4);
			}
			if(e.getActionCommand().equals("30-40")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}
			if(e.getActionCommand().equals("40-50")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}
			if(e.getActionCommand().equals("50+")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}

			if(e.getActionCommand().equals("Menor a mayor")) {
				frame.getUser().getSearcher().getStoreSearcher().setAsc(true);
			}
			else {
				frame.getUser().getSearcher().getStoreSearcher().setAsc(false);
			}
			UnregisteredClient u = (UnregisteredClient) frame.getUser();
			frame.setsProductList(u.searchStoreProduct());
		}
	}
}