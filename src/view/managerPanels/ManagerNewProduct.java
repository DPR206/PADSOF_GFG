package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The type Manager new product.
 * @author Sofia C.L.
 * @version 1.0
 */
public class ManagerNewProduct extends JPanel {
    /**
     * The constant PANEL_MENU.
     */
    public static final String PANEL_MENU = "MENU";
    /**
     * The constant PANEL_COMIC.
     */
    public static final String PANEL_COMIC = "COMIC";
    /**
     * The constant PANEL_FIGURA.
     */
    public static final String PANEL_FIGURA = "FIGURA";
    /**
     * The constant PANEL_JUEGO.
     */
    public static final String PANEL_JUEGO = "JUEGO";
    private final JButton comics = new JButton("AÑADIR UN CÓMIC");
    private final JButton figuras = new JButton("AÑADIR UNA FIGURA");
    private final JButton juegos = new JButton("AÑADIR UN JUEGO");
    private final CardLayout layout = new CardLayout();
    private final JPanel cards = new JPanel(layout);

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Manager new product.
     */
    public ManagerNewProduct() {
        super();

        this.setLayout(new BorderLayout());

        JPanel cosoDeBotones = new JPanel();

        //añadimos los botoncitos
        cosoDeBotones.setLayout(new GridLayout(3, 1));
        cosoDeBotones.add(comics);
        cosoDeBotones.add(figuras);
        cosoDeBotones.add(juegos);

        cards.add(cosoDeBotones, PANEL_MENU);
        this.add(cards);
    }

    /**
     * Show panel.
     * @param name the name
     */
    public void showPanel(String name) {
        layout.show(cards, name);
    }

    /**
     * It gets the cards
     * @return the cards
     */
    public JPanel getCards() {
        return cards;
    }

    /**
     * It gets the comics
     * @return the comics
     */
    public JButton getComics() {
        return comics;
    }

    /**
     * It gets the figuras
     * @return the figuras
     */
    public JButton getFiguras() {
        return figuras;
    }

    /**
     * It gets the juegos
     * @return the juegos
     */
    public JButton getJuegos() {
        return juegos;
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    /**
     * It sets the controller
     * @param c the c
     */
    public void setController(ActionListener c) {
        this.comics.addActionListener(c);
        this.figuras.addActionListener(c);
        this.juegos.addActionListener(c);
    }
}