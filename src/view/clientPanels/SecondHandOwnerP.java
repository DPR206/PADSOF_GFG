package view.clientPanels;

import model.product.ConservationStatus;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * The type Second hand owner p.
 * @author Duna P.R.
 * @version 1.0
 */
public class SecondHandOwnerP extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JButton btnValoration = new JButton("Request Valoration");
    private final JButton btnDelete = new JButton("Delete from wallet");
    private final JButton btnReturn = new JButton("Return");
    private JLabel name;
    private JLabel valuation;
    private JLabel description;
    private JLabel lblImagen;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Second hand owner p.
     */
    public SecondHandOwnerP() {
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
        name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        valuation = new JLabel("");
        valuation.setFont(new Font("Tahoma", Font.PLAIN, 15));
        description = new JLabel("");
        description.setFont(new Font("Tahoma", Font.PLAIN, 15));

        description.setVerticalAlignment(SwingConstants.TOP);

        infoPanel.add(name);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(valuation);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(description);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        Font fuenteBotones = new Font("SansSerif", Font.PLAIN, 18);

        btnValoration.setMaximumSize(new Dimension(250, 100));
        btnValoration.setFont(fuenteBotones);

        btnReturn.setMaximumSize(new Dimension(250, 100));
        btnReturn.setFont(fuenteBotones);

        btnDelete.setMaximumSize(new Dimension(250, 100));
        btnDelete.setFont(fuenteBotones);

        btnValoration.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnReturn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(btnValoration);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnReturn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(btnDelete);
        buttonPanel.add(Box.createVerticalGlue());

        add(infoPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.EAST);

    }

    /**
     * It gets the btn delete
     * @return the btnDelete
     */
    public JButton getBtnDelete() {
        return btnDelete;
    }

    /**
     * It gets the btn return
     * @return the btnReturn
     */
    public JButton getBtnReturn() {
        return btnReturn;
    }

    /**
     * It gets the btn valorationt
     * @return the btnaddCart
     */
    public JButton getBtnValorationt() {
        return btnValoration;
    }

    /**
     * It sets the description text
     * @param texto the texto
     */
    public void setDescriptionText(String texto) {
        this.description.setText("<html><body style='width: 300px;'>" + "Description: " + texto + "</body></html>");
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

    @Override
    public void setName(String s) {
        this.name.setText(s);
    }

    /**
     * It sets the valuation
     * @param price  the price to set
     * @param status the status
     */
    public void setValuation(double price, ConservationStatus status) {
        if (price > 0 && status != null) {
            this.valuation.setText(String.format("Valuation: %.2f€ - %s", price, status));
        }
        this.valuation.setText("Valuation: Pending valuation");
    }

    /**
     * It sets the valuation
     * @param info the info
     */
    public void setValuation(String info) {
        this.valuation.setText("Valuation: " + info);
    }

}