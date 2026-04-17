package ejemplosSwing.mvc.modelo;

import java.util.*;

public class Proyecto {

	private List<Tarea> tareas = new ArrayList<Tarea>();

	public void addTarea(Tarea t) {
		tareas.add(t);
	}
}