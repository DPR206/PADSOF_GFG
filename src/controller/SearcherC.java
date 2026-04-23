package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

		if(e.getActionCommand().equals("Juegos de mesa")) {
			this.frame.getUser().getSearcher().getStoreSearcher().;
		}
		
	}

}
