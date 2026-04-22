package graphicInt;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class SearchPanel extends JPanel{
	private JPanel catFilter = new JPanel();
	
	private JCheckBox jmesa = new JCheckBox("Juegos de mesa"); 
	private JCheckBox jrol = new JCheckBox("Juegos de rol"); 
	private JCheckBox jcarta = new JCheckBox("Juegos de carta"); 
	private JCheckBox figuras = new JCheckBox("Figuras"); 
	private JCheckBox comics = new JCheckBox("comics");
	
	private JPanel puncFilter = new JPanel(new GridLayout(1,1));
	
	private JCheckBox cerouno = new JCheckBox("0-1★"); 
	private JCheckBox unodos = new JCheckBox("1-2★"); 
	private JCheckBox dostres = new JCheckBox("2-3★"); 
	private JCheckBox trescuatro = new JCheckBox("3-4★"); 
	private JCheckBox cuatrocinco = new JCheckBox("4-5★");
	
	private JPanel preciosFilt = new JPanel(new GridLayout(1,1));
	
	private JCheckBox cerodiez = new JCheckBox("0-10"); 
	private JCheckBox diezquince = new JCheckBox("10-15"); 
	private JCheckBox quinceveinte = new JCheckBox("15-20"); 
	private JCheckBox veintetreinta = new JCheckBox("20-30"); 
	private JCheckBox treintacuarenta = new JCheckBox("30-40");
	private JCheckBox cuarentacincuenta = new JCheckBox("40-50");
	private JCheckBox plus50 = new JCheckBox("50+");
	
	JPanel ordenacion = new JPanel(new GridLayout(1,1));
	
	private JRadioButton ascendente = new JRadioButton("Menor a mayor");
	private JRadioButton descendente = new JRadioButton("Mayor a menor");
	
	public SearchPanel() {
		super();
		this.setLayout(new GridLayout(4, 1));
		
		this.catFilter.add(new JLabel("CATEGORÍAS"));
		this.catFilter.add(this.jmesa);
		this.catFilter.add(this.jrol);
		this.catFilter.add(this.jcarta);
		this.catFilter.add(this.figuras);
		this.catFilter.add(this.comics);
		
		this.add(this.catFilter);
		
		this.puncFilter.add(new JLabel("PUNTUACIÓN"));
		this.puncFilter.add(this.cerouno);
		this.puncFilter.add(this.unodos);
		this.puncFilter.add(this.dostres);
		this.puncFilter.add(this.trescuatro);
		this.puncFilter.add(this.cuatrocinco);
		
		this.add(this.puncFilter);
		
		this.preciosFilt.add(new JLabel("PRECIOS"));
		this.preciosFilt.add(cerodiez);
		this.preciosFilt.add(diezquince);
		this.preciosFilt.add(quinceveinte);
		this.preciosFilt.add(veintetreinta);
		this.preciosFilt.add(treintacuarenta);
		this.preciosFilt.add(cuarentacincuenta);
		this.preciosFilt.add(plus50);
		
		this.add(this.preciosFilt);
		
		ButtonGroup grupo = new ButtonGroup();
		
		this.ordenacion.add(new JLabel("ORDENAR"));
		
		grupo.add(this.ascendente);
		grupo.add(descendente);
		
		this.ordenacion.add(this.ascendente);
		this.ordenacion.add(this.descendente);
		
		this.add(this.ordenacion);
	}
}