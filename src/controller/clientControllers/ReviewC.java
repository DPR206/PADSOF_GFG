package controller.clientControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.product.*;
import model.user.RegisteredClient;
import view.clientPanels.ReviewP;

/**
 * The type Review c.
 * @author Duna P.R.
 * @version 1.0
 */
public class ReviewC {
    private ReviewP view;
    private StoreProduct product;
    private RegisteredClient user;

    public ReviewC(ReviewP view, StoreProduct product, RegisteredClient user) {
        this.view = view;
        this.product = product;
        this.user = user;
        
        this.view.addSubmitListener(new SubmitReviewAction());
        this.view.addCancelListener(e -> cerrarVentana());
    }

    private class SubmitReviewAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int estrellas = view.getSelectedStars();
            String comentario = view.getComment();

            if (comentario.isEmpty()) {
                view.mostrarMensaje(
                    "Please, write a comment before sending your review.", 
                    "Empty fiel", 
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            product.addReview(estrellas, comentario, user);
            
            view.mostrarMensaje(
                "Thank You! Your review of " + estrellas + " stars has been saved.", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE
            );
            
            cerrarVentana();
        }
    }

    private void cerrarVentana() {
        view.dispose();
    }
}
