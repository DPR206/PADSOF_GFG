package view.managerPanels;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import view.browserPanels.BrowsePackProductsP;
import view.browserPanels.BrowsePacksComposed;

public class ManagerIndividualComposedPack extends JPanel{
	private Pack p;
    private JTextField idProduct = new JTextField();
    private JTextField packPrecio = new JTextField();
    private JTextField idPack = new JTextField();
    private JButton btnConfirmarProducto = new JButton("CONFIRMAR PRODUCTO");
    private JButton btnConfirmarPrecio = new JButton("CONFIRMAR PRECIO");
    private JButton btnConfirmarPack = new JButton("CONFIRMAR PACK");
    private BrowsePacksComposed browser;

    public ManagerIndividualComposedPack(Pack p) {
    	super();
    	this.p = p;
    	try {
			this.browser = new BrowsePacksComposed();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	this.setLayout(new BorderLayout());

    	this.add(browser, BorderLayout.NORTH);

    	JPanel inserciones = new JPanel();
    	inserciones.setLayout(new GridLayout(9,1));

    	inserciones.add(new JLabel("NOMBRE DEL PRODUCTO A AÑADIR:"));
    	inserciones.add(idProduct);
    	inserciones.add(btnConfirmarProducto);
    	inserciones.add(new JLabel("PRECIO NUEVO:"));
    	inserciones.add(packPrecio);
    	inserciones.add(btnConfirmarPrecio);
    	inserciones.add(new JLabel("ID DEL PACK A AÑADIR:"));
    	inserciones.add(this.idPack);
    	inserciones.add(this.btnConfirmarPack);
    	this.add(inserciones, BorderLayout.EAST);
    	
    	this.add(this.browser);
    }
    
    public BrowsePacksComposed getBrowser() {
    	return this.browser;
    }
    public JButton getConfirmarProduct() {
    	return this.btnConfirmarProducto;
    }
    public JButton getConfirmarPrecio() {
    	return this.btnConfirmarPrecio;
    }
    public JButton getConfirmarPackId() {
    	return this.btnConfirmarPack;
    }
    public JTextField getIdProductText() {
    	return this.idProduct;
    }
    public JTextField getPackPriceText() {
    	return this.packPrecio;
    }
    public JTextField getPackIdText() {
    	return this.idPack;
    }
    public void setControllerConfirmarProduct(ActionListener e) {
    	this.btnConfirmarProducto.addActionListener(e);
    }
    public void setControllerConfirmarPrecio(ActionListener e) {
    	this.btnConfirmarPrecio.addActionListener(e);
    }
}

