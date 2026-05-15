package view.managerPanels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.product.Pack;
import view.browserPanels.BrowsePackProductsP;
import view.browserPanels.BrowsePacksPackP;

public class ManagerIndividualComposedPack extends JPanel{
	private Pack p;
    private JTextField idProduct = new JTextField();
    private JTextField packPrecio = new JTextField();
    private JButton btnConfirmarProducto = new JButton("CONFIRMAR PRODUCTO");
    private JButton btnConfirmarPrecio = new JButton("CONFIRMAR PRECIO");
    private BrowsePacksPackP browser;

    public ManagerIndividualComposedPack(Pack p) {
    	super();
    	this.p = p;
    	this.browser = new BrowsePacksPackP(p);

    	this.setLayout(new BorderLayout());

    	this.add(browser, BorderLayout.NORTH);

    	JPanel inserciones = new JPanel();
    	inserciones.setLayout(new GridLayout(4,1));

    	inserciones.add(new JLabel("ID DEL PRODUCTO A AÑADIR:"));
    	inserciones.add(idProduct, this.btnConfirmarProducto);
    	inserciones.add(new JLabel("PRECIO NUEVO:"));
    	inserciones.add(packPrecio, this.btnConfirmarPrecio);
    	this.add(inserciones, BorderLayout.EAST);
    	
    	this.add(this.browser);
    }
    
    public BrowsePacksPackP getBrowser() {
    	return this.browser;
    }
    public JButton getConfirmarProduct() {
    	return this.btnConfirmarProducto;
    }
    public JButton getConfirmarPrecio() {
    	return this.btnConfirmarPrecio;
    }
    public JTextField getIdProductText() {
    	return this.idProduct;
    }
    public JTextField getPackPriceText() {
    	return this.packPrecio;
    }
    public void setControllerConfirmarProduct(ActionListener e) {
    	this.btnConfirmarProducto.addActionListener(e);
    }
    public void setControllerConfirmarPrecio(ActionListener e) {
    	this.btnConfirmarPrecio.addActionListener(e);
    }
}

