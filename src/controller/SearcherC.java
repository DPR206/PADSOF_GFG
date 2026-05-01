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

    public SearcherC(App frame, Store model, SearchPanel view) {
		this.view = view;
		this.frame = frame;
		this.model = model;
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		List<Category> categories = new ArrayList<>();
		this.frame.getUser().getSearcher().getStoreSearcher().clearFilters();

		if(e.getActionCommand().equals("Aplicar filtros")) {
			/*METEMOS LAS CATEGORÍAS PRIMERO*/
			if(view.getJmesa().isSelected()) {
				categories.add(model.getCategoryFromName("Juegos de mesa"));
			}
			if(view.getJrol().isSelected()) {
				categories.add(model.getCategoryFromName("Juegos de rol"));
			}
			if(view.getJcarta().isSelected()) {
				categories.add(model.getCategoryFromName("Juegos de cartas"));
			}
			if(view.getFiguras().isSelected()) {
				categories.add(model.getCategoryFromName("Figuras"));
			}
			if(view.getComics().isSelected()) {
				categories.add(model.getCategoryFromName("Cómics"));
			}
			if(categories.isEmpty() == false) {
				CategoryFilter c = new CategoryFilter(categories);
				frame.getUser().getSearcher().getStoreSearcher().addCategoryFilter(c);
			}

			/*Ahora metemos los filtros de puntuación*/


			if(view.getCerouno().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(0, 1);
			}
			if(view.getUnodos().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(1,2);
			}
			if(view.getDostres().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(2,3);
			}
			if(view.getTrescuatro().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(3,4);
			}
			if(view.getCuatrocinco().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}


			/*Ahora metemos el filtro de los precios*/

			/*if(e.getActionCommand().equals("0-10")) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(0, 1);
			} Transformado en lo de debajo */
			if(view.getCerodiez().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(0, 10);
			}
			if(view.getDiezquince().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPriceFilter(10,15);
			}
			if(view.getQuinceveinte().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(2,3);
			}
			if(view.getVeintetreinta().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(3,4);
			}
			if(view.getTreintacuarenta().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}
			if(view.getCuarentacincuenta().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}
			if(view.getPlus50().isSelected()) {
				frame.getUser().getSearcher().getStoreSearcher().addPunctuationFilter(4,5);
			}

			if(view.getAscendente().isSelected()) {
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