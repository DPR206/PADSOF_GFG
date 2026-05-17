package view.clientPanels;

import javax.swing.*;
import java.awt.*;

/**
 * The type Search panel.
 * @author Sofia C.L.
 * @version 1.0
 */
public class SearchPanel extends JPanel {
    private final JRadioButton jmesa = new JRadioButton("Juegos de mesa");
    private final JRadioButton jrol = new JRadioButton("Juegos de rol");
    private final JRadioButton jcarta = new JRadioButton("Juegos de carta");
    private final JRadioButton figuras = new JRadioButton("Figuras");
    private final JRadioButton comics = new JRadioButton("Comics");
    private final JCheckBox cerouno = new JCheckBox("0-1★");
    private final JCheckBox unodos = new JCheckBox("1-2★");
    private final JCheckBox dostres = new JCheckBox("2-3★");
    private final JCheckBox trescuatro = new JCheckBox("3-4★");
    private final JCheckBox cuatrocinco = new JCheckBox("4-5★");
    private final JCheckBox cerodiez = new JCheckBox("0-10");
    private final JCheckBox diezquince = new JCheckBox("10-15");
    private final JCheckBox quinceveinte = new JCheckBox("15-20");
    private final JCheckBox veintetreinta = new JCheckBox("20-30");
    private final JCheckBox treintacuarenta = new JCheckBox("30-40");
    private final JCheckBox cuarentacincuenta = new JCheckBox("40-50");
    private final JCheckBox plus50 = new JCheckBox("50+");
    private final JButton aplicar = new JButton("Aplicar filtros");
    private final JRadioButton ascendente = new JRadioButton("Menor a mayor");

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Instantiates a new Search panel.
     */
    public SearchPanel() {
        super();
        this.setLayout(new GridLayout(4, 1));

        JPanel catFilter = new JPanel(new GridLayout(1, 1));
        catFilter.add(new JLabel("CATEGORÍAS"));
        catFilter.add(this.jmesa);
        catFilter.add(this.jrol);
        catFilter.add(this.jcarta);
        catFilter.add(this.figuras);
        catFilter.add(this.comics);

        this.add(catFilter);

        JPanel puncFilter = new JPanel(new GridLayout(1, 1));
        puncFilter.add(new JLabel("PUNTUACIÓN"));
        puncFilter.add(this.cerouno);
        puncFilter.add(this.unodos);
        puncFilter.add(this.dostres);
        puncFilter.add(this.trescuatro);
        puncFilter.add(this.cuatrocinco);

        this.add(puncFilter);

        JPanel preciosFilt = new JPanel(new GridLayout(1, 1));
        preciosFilt.add(new JLabel("PRECIOS"));
        preciosFilt.add(cerodiez);
        preciosFilt.add(diezquince);
        preciosFilt.add(quinceveinte);
        preciosFilt.add(veintetreinta);
        preciosFilt.add(treintacuarenta);
        preciosFilt.add(cuarentacincuenta);
        preciosFilt.add(plus50);

        this.add(preciosFilt);

        ButtonGroup grupo = new ButtonGroup();

        JPanel ordenacion = new JPanel(new GridLayout(1, 1));
        ordenacion.add(new JLabel("ORDENAR"));

        grupo.add(this.ascendente);
        JRadioButton descendente = new JRadioButton("Mayor a menor");
        grupo.add(descendente);

        ordenacion.add(this.ascendente);
        ordenacion.add(descendente);

        this.add(ordenacion);
        this.add(this.aplicar);
    }

    /**
     * It gets the aplicar
     * @return the aplicar
     */
    public JButton getAplicar() {
        return aplicar;
    }

    /**
     * It gets the ascendente
     * @return the ascendente
     */
    public JRadioButton getAscendente() {
        return ascendente;
    }

    /**
     * It gets the cerodiez
     * @return the cerodiez
     */
    public JCheckBox getCerodiez() {
        return cerodiez;
    }

    /**
     * It gets the cerouno
     * @return the cerouno
     */
    public JCheckBox getCerouno() {
        return cerouno;
    }

    /**
     * It gets the comics
     * @return the comics
     */
    public JRadioButton getComics() {
        return comics;
    }

    /**
     * It gets the cuarentacincuenta
     * @return the cuarentacincuenta
     */
    public JCheckBox getCuarentacincuenta() {
        return cuarentacincuenta;
    }

    /**
     * It gets the cuatrocinco
     * @return the cuatrocinco
     */
    public JCheckBox getCuatrocinco() {
        return cuatrocinco;
    }

    /**
     * It gets the diezquince
     * @return the diezquince
     */
    public JCheckBox getDiezquince() {
        return diezquince;
    }

    /**
     * It gets the dostres
     * @return the dostres
     */
    public JCheckBox getDostres() {
        return dostres;
    }

    /**
     * It gets the figuras
     * @return the figuras
     */
    public JRadioButton getFiguras() {
        return figuras;
    }

    /**
     * It gets the jcarta
     * @return the jcarta
     */
    public JRadioButton getJcarta() {
        return jcarta;
    }

    /**
     * It gets the jmesa
     * @return the jmesa
     */
    public JRadioButton getJmesa() {
        return jmesa;
    }

    /**
     * It gets the jrol
     * @return the jrol
     */
    public JRadioButton getJrol() {
        return jrol;
    }

    /**
     * It gets the plus 50
     * @return the plus 50
     */
    public JCheckBox getPlus50() {
        return plus50;
    }

    /**
     * It gets the quinceveinte
     * @return the quinceveinte
     */
    public JCheckBox getQuinceveinte() {
        return quinceveinte;
    }

    /**
     * It gets the treintacuarenta
     * @return the treintacuarenta
     */
    public JCheckBox getTreintacuarenta() {
        return treintacuarenta;
    }

    /**
     * It gets the trescuatro
     * @return the trescuatro
     */
    public JCheckBox getTrescuatro() {
        return trescuatro;
    }

    /**
     * It gets the unodos
     * @return the unodos
     */
    public JCheckBox getUnodos() {
        return unodos;
    }

    /**
     * It gets the veintetreinta
     * @return the veintetreinta
     */
    public JCheckBox getVeintetreinta() {
        return veintetreinta;
    }
}