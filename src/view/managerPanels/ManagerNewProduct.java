package view.managerPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerNewProduct extends JPanel {
    public static final String PANEL_MENU = "MENU";
    public static final String PANEL_COMIC = "COMIC";
    public static final String PANEL_FIGURA = "FIGURA";
    public static final String PANEL_JUEGO = "JUEGO";
    private JButton comics = new JButton("AÑADIR UN CÓMIC");
    private JButton figuras = new JButton("AÑADIR UNA FIGURA");
    private JButton juegos = new JButton("AÑADIR UN JUEGO");
    private CardLayout layout = new CardLayout();
    private JPanel cards = new JPanel(layout);

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
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

    public void showPanel(String name) {
        layout.show(cards, name);
    }

    public JPanel getCards() {
        return cards;
    }

    public JButton getComics() {
        return comics;
    }

    public JButton getFiguras() {
        return figuras;
    }

    public JButton getJuegos() {
        return juegos;
    }

    @Override
    public CardLayout getLayout() {
        return layout;
    }

    public void setController(ActionListener c) {
        this.comics.addActionListener(c);
        this.figuras.addActionListener(c);
        this.juegos.addActionListener(c);
    }
}