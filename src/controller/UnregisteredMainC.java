package controller;

import model.store.Store;
import model.user.UnregisteredClient;
import model.user.User;
import view.App;
import view.UnregisteredMainP;

import javax.swing.text.BadLocationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnregisteredMainC implements ActionListener {
    private final UnregisteredMainP view; /* view -> panel */
    private final App frame; /* view -> frame */
    private final Store model; /* model */
    private final User c = new UnregisteredClient(false);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public UnregisteredMainC(App frame, Store model) {
        this.frame = frame;
        this.view = frame.getUnregisteredMainPanel();
        this.model = model;

        updateControllers();
    }

    public void updateControllers() {
        view.getBrowsePanel().setController(new BrowseStoreC(frame, model, view.getBrowsePanel()));
        view.getFilterPanel().setController(new SearcherC(frame, model, view.getFilterPanel()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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