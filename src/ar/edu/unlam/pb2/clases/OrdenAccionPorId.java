package ar.edu.unlam.pb2.clases;

import java.util.Comparator;

public class OrdenAccionPorId implements Comparator<Accion>{



	@Override
	public int compare(Accion o1, Accion o2) {
		if(o1.getIdAccion().compareTo(o2.getIdAccion())== 0) {
			return o2.getIdAccion().compareTo(o1.getIdAccion());
		}
		return o1.getIdAccion().compareTo(o2.getIdAccion());
	}


	

}
