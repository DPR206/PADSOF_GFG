package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

import model.product.Pack;
import model.product.StoreProduct;
import view.banners.BannerManager;
import view.miniPanels.PackMiniEdit;
import view.miniPanels.StoreProductMiniDelete;

public class SPManageIndividualPack extends JPanel{
	private Pack p;
	private BannerManager mb = new BannerManager();
	private JTextField nameProduct = new JTextField();
	private JTextField packPrecio = new JTextField();
	private JButton btnConfirmarProducto = new JButton("CONFIRMAR PRODUCTO");
	private JButton btnConfirmarPrecio = new JButton("CONFIRMAR PRECIO");
	
	public SPManageIndividualPack(Pack p) {
		this.p = p;
		
		this.setLayout(new BorderLayout());
		this.add(this.mb, BorderLayout.NORTH);
		
		JPanel scrollPanels = new JPanel();
		scrollPanels.setLayout(new GridLayout(2,0));
		
		JPanel aux1 = new JPanel();
		JPanel aux2 = new JPanel();
		
		//añado los minipacks
		
		HashSet<Pack> listPacks = this.p.getPacks();
		
		int i = 1;
		
		for(Pack pack: listPacks) {
			try {
				aux1.add(new PackMiniEdit(pack, i));
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		JScrollPane scroll1 = new JScrollPane(aux1);
		
		//añado los miniproductos en el otro
		
		i = 1;
		
		List<StoreProduct> products = this.p.getProducts();
		for(StoreProduct sp: products) {
			try {
				aux2.add(new StoreProductMiniDelete(sp, i));
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		
		JScrollPane scroll2 = new JScrollPane(aux2);
		scrollPanels.add(scroll1);
		scrollPanels.add(scroll2);
		
		this.add(scrollPanels, BorderLayout.CENTER);
		JPanel panelCreacion = new JPanel();
		
		panelCreacion.setBackground(new Color(245, 241, 236)); // fondo beige claro
        panelCreacion.setLayout(new BorderLayout(10, 10));
        
        JPanel camposPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        camposPanel.setBackground(new Color(245, 241, 236));
        
        JLabel lblProducto = new JLabel("Añadir producto");
        btnConfirmarProducto.setForeground(Color.WHITE);
        JLabel precioPack = new JLabel("Precio del pack");
        camposPanel.add(lblProducto);
        camposPanel.add(this.nameProduct);
        camposPanel.add(this.btnConfirmarProducto);
        camposPanel.add(precioPack);
        camposPanel.add(this.packPrecio);
        camposPanel.add(this.btnConfirmarPrecio);
        
        this.add(camposPanel, BorderLayout.SOUTH);
	}
}
