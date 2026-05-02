package view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class ManagerMainP extends JPanel {
	/*Botones de la parte superior para volver atrás o algo*/
	private BannerManager banner = new BannerManager();
	/*Botones para administrar cosas*/
	private JButton packs = new JButton("Packs");
	private JButton productoNuevo = new JButton("Productos nuevos");
	private JButton annadirProductos = new JButton("Añadir productos");
	private JButton empleados = new JButton("Empleados");
	private JButton estadisticas = new JButton("Estadísticas");
	private JButton descuentos = new JButton("Descuentos");
	private JButton parametros = new JButton("Parámetros");
	
    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ManagerMainP() {
    	super();
    	this.setLayout(new BorderLayout());
    	this.add(banner, BorderLayout.NORTH);
    	
    	//ahora el cacho principal
    	
    	JPanel cosoDeBotones = new JPanel();
    	
    	//añadimos los botoncitos
    	cosoDeBotones.setLayout(new GridLayout(2, 6));
    	cosoDeBotones.add(this.packs);
    	cosoDeBotones.add(this.productoNuevo);
    	cosoDeBotones.add(this.annadirProductos);
    	cosoDeBotones.add(this.empleados);
    	cosoDeBotones.add(this.estadisticas);
    	cosoDeBotones.add(this.descuentos);
    	cosoDeBotones.add(this.parametros);
    	
    	this.add(cosoDeBotones, BorderLayout.CENTER);
    }

    /**
     * It makes it possible to assign a controller to this panel's components
     * @param c the desired controller
     */
    public void setController(ActionListener c) {
        this.packs.addActionListener(c);
        this.productoNuevo.addActionListener(c);
        this.annadirProductos.addActionListener(c);
        this.empleados.addActionListener(c);
        this.estadisticas.addActionListener(c);
        this.parametros.addActionListener(c);
    }
    
    public BannerManager getBanner() {
    	return this.banner;
    }
}