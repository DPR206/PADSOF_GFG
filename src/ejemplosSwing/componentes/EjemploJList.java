package ejemplosSwing.componentes;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EjemploJList {

	public static void main(String[] args) {
		JFrame ventana = new JFrame("Mi GUI");

		// obtener contenedor, asignar layout
		Container contenedor = ventana.getContentPane();
		contenedor.setLayout(new FlowLayout());

		// crear componentes
		JLabel etiqueta = new JLabel("Selecciona uno de la lista");
		 // Crear un array con los elementos de la lista
		String[] personas =  {"ana","eduardo","esther","jose","juan","luis","marta","miguel","zoe"};

		// Crear la lista, pasando el modelo como par�metro
		JList<String> lista = new JList<>(personas);

		// asociar acciones a componentes
		lista.addListSelectionListener(
		  new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent ev) {
		    	JList<String> localJList = (JList<String>) ev.getSource();
		    	System.out.println( lista.getSelectedValue() );
		    	if (! ev.getValueIsAdjusting() ) {
		    	  String valorSeleccionado = (String) lista.getSelectedValue();
		          JOptionPane.showMessageDialog(ventana, "Seleccionaste: " + valorSeleccionado);
		    	}
		    }
		  }
		);

		// Por defecto se pueden seleccionar varias filas en la lista.
		// Para que solo se pueda seleccionar una, usar setSelectionMode
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Es aconsejable crear una barra de scroll para la lista,
		// por si el numero de elementos supera el tamanyo previsto
		JScrollPane scroll = new JScrollPane(lista);

		// Anyadir el scroll con la lista al panel donde se vaya a mostrar
		JPanel ejemploList = new JPanel();
		ejemploList.add(scroll);

		// anyadir componentes al contenedor
		contenedor.add(etiqueta);
		contenedor.add(ejemploList);
		ejemploList.setVisible(true);

		// mostrar ventana
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setSize(200,340);
		ventana.setVisible(true);
	}
}