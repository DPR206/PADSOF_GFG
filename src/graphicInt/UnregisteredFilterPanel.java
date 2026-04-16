package graphicInt;

import java.awt.GridLayout;

import javax.swing.*;

public class UnregisteredFilterPanel{
	public static void main(String[] args) {
		
		JFrame ventana = new JFrame("FilterThing");
		ventana.setSize(400, 300);
		ventana.setVisible(true);
		
		JPanel catFilter = new JPanel(new GridLayout(15,10));
		
		JCheckBox jmesa = new JCheckBox("Juegos de mesa"); 
		JCheckBox jrol = new JCheckBox("Juegos de rol"); 
		JCheckBox jcarta = new JCheckBox("Juegos de carta"); 
		JCheckBox figuras = new JCheckBox("Figuras"); 
		JCheckBox comics = new JCheckBox("comics");
		
		catFilter.add(new JLabel("CATEGORÍAS"));
		catFilter.add(jmesa);
		catFilter.add(jrol);
		catFilter.add(jcarta);
		catFilter.add(figuras);
		catFilter.add(comics);
		
		JPanel puncFilter = new JPanel(new GridLayout(15,10));
		
		JCheckBox cerouno = new JCheckBox("0-1"); 
		JCheckBox unodos = new JCheckBox("1-2"); 
		JCheckBox dostres = new JCheckBox("2-3"); 
		JCheckBox trescuatro = new JCheckBox("3-4"); 
		JCheckBox cuatrocinco = new JCheckBox("4-5");
		
		puncFilter.add(new JLabel("PUNTUACIÓN"));
		puncFilter.add(cerouno);
		puncFilter.add(unodos);
		puncFilter.add(dostres);
		puncFilter.add(trescuatro);
		puncFilter.add(cuatrocinco);
		
		JPanel preciosFilt = new JPanel(new GridLayout(15, 10));
		
		JCheckBox cerodiez = new JCheckBox("0-10"); 
		JCheckBox diezquince = new JCheckBox("10-15"); 
		JCheckBox quinceveinte = new JCheckBox("15-20"); 
		JCheckBox veintetreinta = new JCheckBox("20-30"); 
		JCheckBox treintacuarenta = new JCheckBox("30-40");
		JCheckBox cuarentacincuenta = new JCheckBox("40-50");
		JCheckBox plus50 = new JCheckBox("50+");
		
		preciosFilt.add(new JLabel("PRECIOS"));
		
		puncFilter.add(cerodiez);
		puncFilter.add(diezquince);
		puncFilter.add(quinceveinte);
		puncFilter.add(veintetreinta);
		puncFilter.add(treintacuarenta);
		puncFilter.add(cuarentacincuenta);
		puncFilter.add(plus50);
		
		JPanel ordenacion = new JPanel(new GridLayout(15,10));
		
		JRadioButton ascendente = new JRadioButton("Menor a mayor");
		JRadioButton descendente = new JRadioButton("Mayor a menor");
		
		ordenacion.add(new JLabel("ORDENAR"));
		ordenacion.add(ascendente);
		ordenacion.add(descendente);
		
		
	}	
}