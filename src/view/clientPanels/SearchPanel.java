package view.clientPanels;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    JPanel ordenacion = new JPanel(new GridLayout(1, 1));
    private JPanel catFilter = new JPanel(new GridLayout(1, 1));
    private JRadioButton jmesa = new JRadioButton("Juegos de mesa");
    private JRadioButton jrol = new JRadioButton("Juegos de rol");
    private JRadioButton jcarta = new JRadioButton("Juegos de carta");
    private JRadioButton figuras = new JRadioButton("Figuras");
    private JRadioButton comics = new JRadioButton("Comics");
    private JPanel puncFilter = new JPanel(new GridLayout(1, 1));
    private JCheckBox cerouno = new JCheckBox("0-1★");
    private JCheckBox unodos = new JCheckBox("1-2★");
    private JCheckBox dostres = new JCheckBox("2-3★");
    private JCheckBox trescuatro = new JCheckBox("3-4★");
    private JCheckBox cuatrocinco = new JCheckBox("4-5★");
    private JPanel preciosFilt = new JPanel(new GridLayout(1, 1));
    private JCheckBox cerodiez = new JCheckBox("0-10");
    private JCheckBox diezquince = new JCheckBox("10-15");
    private JCheckBox quinceveinte = new JCheckBox("15-20");
    private JCheckBox veintetreinta = new JCheckBox("20-30");
    private JCheckBox treintacuarenta = new JCheckBox("30-40");
    private JCheckBox cuarentacincuenta = new JCheckBox("40-50");
    private JCheckBox plus50 = new JCheckBox("50+");
    private JButton aplicar = new JButton("Aplicar filtros");
    private JRadioButton ascendente = new JRadioButton("Menor a mayor");
    private JRadioButton descendente = new JRadioButton("Mayor a menor");

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
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
        this.add(this.aplicar);
    }

    public JButton getAplicar() {
        return aplicar;
    }

    public JRadioButton getAscendente() {
        return ascendente;
    }

    public JPanel getCatFilter() {
        return catFilter;
    }

    public JCheckBox getCerodiez() {
        return cerodiez;
    }

    public JCheckBox getCerouno() {
        return cerouno;
    }

    public JRadioButton getComics() {
        return comics;
    }

    public JCheckBox getCuarentacincuenta() {
        return cuarentacincuenta;
    }

    public JCheckBox getCuatrocinco() {
        return cuatrocinco;
    }

    public JRadioButton getDescendente() {
        return descendente;
    }

    public JCheckBox getDiezquince() {
        return diezquince;
    }

    public JCheckBox getDostres() {
        return dostres;
    }

    public JRadioButton getFiguras() {
        return figuras;
    }

    public JRadioButton getJcarta() {
        return jcarta;
    }

    public JRadioButton getJmesa() {
        return jmesa;
    }

    public JRadioButton getJrol() {
        return jrol;
    }

    public JPanel getOrdenacion() {
        return ordenacion;
    }

    public JCheckBox getPlus50() {
        return plus50;
    }

    public JPanel getPreciosFilt() {
        return preciosFilt;
    }

    public JPanel getPuncFilter() {
        return puncFilter;
    }

    public JCheckBox getQuinceveinte() {
        return quinceveinte;
    }

    public JCheckBox getTreintacuarenta() {
        return treintacuarenta;
    }

    public JCheckBox getTrescuatro() {
        return trescuatro;
    }

    public JCheckBox getUnodos() {
        return unodos;
    }

    public JCheckBox getVeintetreinta() {
        return veintetreinta;
    }
}