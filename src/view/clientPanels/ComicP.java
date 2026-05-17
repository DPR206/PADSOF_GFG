package view.clientPanels;

import model.product.Review;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.List;

/**
 * The type Comic p.
 * @author Duna P.R.
 * @version 1.0
 */
public class ComicP extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton btnaddCart = new JButton("Add to cart");
    private final JButton btnReturn = new JButton("Return");
    private JLabel name;
    private JLabel author;
    private JLabel editorial;
    private JLabel year;
    private JLabel pages;
    private JLabel categories;
    private JLabel price;
    private JLabel rating;
    private JLabel description;
    private JLabel stock;
    private JLabel lblImagen;
    private JSpinner unitSpinner;
    private JList<String> listReviews;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Comic p.
     */
    public ComicP() {
        configurarEstructura();
    }

    private void configurarEstructura() {

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JPanel imageWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lblImagen = new JLabel("Cargando imagen...");
        lblImagen.setPreferredSize(new Dimension(250, 350));
        lblImagen.setVerticalAlignment(SwingConstants.TOP);
        imageWrapper.add(lblImagen);
        add(imageWrapper, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        name = new JLabel("");
        author = new JLabel("");
        editorial = new JLabel("");
        year = new JLabel("");
        pages = new JLabel("");
        price = new JLabel("");
        price.setForeground(new Color(0, 128, 128));
        price.setFont(new Font("SansSerif", Font.BOLD, 16));
        description = new JLabel("");
        rating = new JLabel("");
        categories = new JLabel("");

        description.setVerticalAlignment(SwingConstants.TOP);

        rating = new JLabel("Valoración: ⭐⭐⭐⭐⭐");
        rating.setFont(new Font("SansSerif", Font.PLAIN, 16));
        rating.setForeground(new Color(218, 165, 32));

        infoPanel.add(name);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(author);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(editorial);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(year);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(pages);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(categories);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(price);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(rating);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(description);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Font fuenteBotones = new Font("SansSerif", Font.PLAIN, 18);

        btnaddCart.setMaximumSize(new Dimension(250, 100));
        btnaddCart.setFont(fuenteBotones);

        btnReturn.setMaximumSize(new Dimension(250, 100));
        btnReturn.setFont(fuenteBotones);

        btnaddCart.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnaddCart);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnReturn);
        buttonPanel.add(Box.createVerticalGlue());

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

        JPanel containerSouth = new JPanel(new BorderLayout(20, 10));

        JPanel purchasePanel = new JPanel();
        purchasePanel.setLayout(new BoxLayout(purchasePanel, BoxLayout.Y_AXIS));

        stock = new JLabel("Stock: ");
        stock.setFont(new Font("SansSerif", Font.BOLD, 15));
        stock.setAlignmentX(Component.LEFT_ALIGNMENT);

        SpinnerModel model = new SpinnerNumberModel(1, 1, 100, 1);
        unitSpinner = new JSpinner(model);
        unitSpinner.setFont(new Font("SansSerif", Font.PLAIN, 15));
        unitSpinner.setMaximumSize(new Dimension(120, 50));
        unitSpinner.setAlignmentX(Component.LEFT_ALIGNMENT);

        purchasePanel.add(stock);
        purchasePanel.add(Box.createVerticalStrut(10));
        purchasePanel.add(unitSpinner);

        listReviews = new JList<>();
        JScrollPane scrollReviews = new JScrollPane(listReviews);
        scrollReviews.setPreferredSize(new Dimension(300, 150));
        scrollReviews.setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "User reviews", 0, 0,
                        new Font("SansSerif", Font.BOLD, 15)));

        containerSouth.add(purchasePanel, BorderLayout.WEST);
        containerSouth.add(scrollReviews, BorderLayout.CENTER);

        add(containerSouth, BorderLayout.SOUTH);

    }

    /**
     * It gets the btn return
     * @return the btnReturn
     */
    public JButton getBtnReturn() {
        return btnReturn;
    }

    /**
     * It gets the btnadd cart
     * @return the btnaddCart
     */
    public JButton getBtnaddCart() {
        return btnaddCart;
    }

    /**
     * It gets the unit spinner
     * @return the unitSpinner
     */
    public JSpinner getUnitSpinner() {
        return unitSpinner;
    }

    /**
     * It sets the author
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author.setText(author);
    }

    /**
     * It sets the categories
     * @param categories the categories to set
     */
    public void setCategories(String categories) {
        this.categories.setText(categories);
    }

    /**
     * It sets the description text
     * @param texto the texto
     */
    public void setDescriptionText(String texto) {
        this.description.setText(
                "<html><body style='width: 300px;'>" + "<b>Description:</b> " + texto + "</body></html>");
    }

    /**
     * It sets the editorial
     * @param editorial the editorial to set
     */
    public void setEditorial(String editorial) {
        this.editorial.setText(editorial);
    }

    /**
     * It sets the image
     * @param ruta the ruta
     */
    public void setImage(String ruta) {
        try {
            ImageIcon iconOriginal = new ImageIcon(ruta);

            int width = lblImagen.getPreferredSize().width;
            int height = lblImagen.getPreferredSize().height;

            Image imgEscalada = iconOriginal.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

            lblImagen.setIcon(new ImageIcon(imgEscalada));
            lblImagen.setText("");

        } catch (Exception e) {
            lblImagen.setText("Error al cargar imagen");
            System.err.println("No se pudo cargar: " + ruta);
        }
    }

    /**
     * It sets the max stock
     * @param realStock the real stock
     */
    public void setMaxStock(int realStock) {
        SpinnerNumberModel model = (SpinnerNumberModel) unitSpinner.getModel();

        model.setMaximum(realStock);

        if (realStock <= 0) {
            unitSpinner.setValue(0);
            unitSpinner.setEnabled(false);
            btnaddCart.setEnabled(false);
            stock.setText("OUT OF STOCK!");
            stock.setForeground(Color.RED);
        } else {
            unitSpinner.setEnabled(true);
            btnaddCart.setEnabled(true);
            if ((int) unitSpinner.getValue() > realStock) {
                unitSpinner.setValue(realStock);
            }
        }
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name.setText(name);
    }

    /**
     * It sets the pages
     * @param i the pages to set
     */
    public void setPages(int i) {
        this.pages.setText(i + " pages");
    }

    /**
     * It sets the price
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price.setText(String.format("Price: %.2f€", price));
    }

    /**
     * It sets the rating
     * @param score the score
     */
    public void setRating(double score) {
        int estrellasEnteras = (int) Math.round(score);

        String estrellas = "★".repeat(Math.max(0, Math.min(5, estrellasEnteras)));
        String vacias = "☆".repeat(Math.max(0, 5 - estrellasEnteras));

        this.rating.setText(String.format("Rating: %.1f / 5 %s%s", score, estrellas, vacias));

        this.rating.setFont(new Font("Monospaced", Font.BOLD, 16));
    }

    /**
     * It sets the stock
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        if (stock == 0) {
            this.stock.setText("Out of stock");
            this.stock.setForeground(Color.RED);
        } else {
            this.stock.setText("Stock: " + stock);
        }
    }

    /**
     * It sets the valoraciones
     * @param list the list
     */
    public void setValoraciones(List<Review> list) {
        if (list == null || list.isEmpty()) {
            String[] emptyMessage = {"Aún no hay valoraciones para este cómic."};
            listReviews.setListData(emptyMessage);
            listReviews.setEnabled(false);
        } else {
            String[] mensajes =
                    list.stream().map(review -> review.getAuthor() + ": " + review.getComment()).toArray(String[]::new);

            listReviews.setListData(mensajes);
            listReviews.setEnabled(true);
        }
    }

    /**
     * It sets the year
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year.setText(year);
    }

}