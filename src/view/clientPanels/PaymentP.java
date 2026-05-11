package view.clientPanels;

import java.awt.*;

import javax.swing.*;

public class PaymentP extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txtTarjeta;
    private JButton btnConfirmar, btnCancelar;
    private JLabel lblImporte;

    public PaymentP(Frame padre, double importeTotal) {
        super(padre, "Confirm Payment", true);
        setSize(400, 250);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel(new GridLayout(4, 1, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        lblImporte = new JLabel("Importe Total: " + String.format("%.2f €", importeTotal));
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

    public String getNumeroTarjeta() { return txtTarjeta.getText(); }
    public JButton getBtnConfirmar() { return btnConfirmar; }
    public JButton getBtnCancelar() { return btnCancelar; }
}