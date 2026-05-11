package view.managerPanels;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.product.Pack;
import view.browserPanels.BrowseStorePInPack;

public class ManagerIndividualPack extends JPanel{
	private Pack p;
    private JTextField idProduct = new JTextField();
    private JTextField packPrecio = new JTextField();
    private JButton btnConfirmarProducto = new JButton("CONFIRMAR PRODUCTO");
    private JButton btnConfirmarPrecio = new JButton("CONFIRMAR PRECIO");
    
    public ManagerIndividualPack(Pack p) {
    	super();
    	this.p = p;
    	
    	this.setLayout(new BorderLayout());
    	
    	BrowseStorePInPack browser = new BrowseStorePInPack(p);
    	this.add(browser, BorderLayout.NORTH);
    	
    	JPanel inserciones = new JPanel();
    	inserciones.setLayout(new GridLayout(4,1));
    	
    	inserciones.add(new JLabel("ID DEL PRODUCTO A AÑADIR:"));
    	inserciones.add(idProduct, this.btnConfirmarProducto);
    	inserciones.add(new JLabel("PRECIO NUEVO:"));
    	inserciones.add(packPrecio, this.btnConfirmarPrecio);
    	this.add(inserciones, BorderLayout.SOUTH);
    }
}
