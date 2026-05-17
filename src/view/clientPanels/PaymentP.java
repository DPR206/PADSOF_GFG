package view.clientPanels;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * The type Payment p.
 * @author Duna P.R.
 * @version 1.0
 */
public class PaymentP extends JDialog {

    @Serial
    private static final long serialVersionUID = 1L;
    private final JTextField txtTarjeta;
    private final JButton btnConfirmar;
    private final JButton btnCancelar;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Payment p.
     * @param padre        the padre
     * @param importeTotal the importe total
     */
    public PaymentP(Frame padre, double importeTotal) {
        super(padre, "Confirm Payment", true);
        setSize(400, 250);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel(new GridLayout(4, 1, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblImporte = new JLabel("Importe Total: " + String.format("%.2f €", importeTotal));
        lblImporte.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblImporte.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblInstruccion = new JLabel("Introduce your card number:");
        txtTarjeta = new JTextField();
        txtTarjeta.setFont(new Font("Monospaced", Font.PLAIN, 16));

        panelCentral.add(lblImporte);
        panelCentral.add(lblInstruccion);
        panelCentral.add(txtTarjeta);

        JPanel panelBotones = new JPanel(new FlowLayout());
        btnConfirmar = new JButton("Confirm Payment");
        btnCancelar = new JButton("Cancel");

        panelBotones.add(btnCancelar);
        panelBotones.add(btnConfirmar);

        add(panelCentral, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    /**
     * It gets the btn cancelar
     * @return the btn cancelar
     */
    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    /**
     * It gets the btn confirmar
     * @return the btn confirmar
     */
    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }

    /**
     * It gets the numero tarjeta
     * @return the numero tarjeta
     */
    public String getNumeroTarjeta() {
        return txtTarjeta.getText();
    }
}