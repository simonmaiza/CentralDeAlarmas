package ar.edu.unlam.pb2.interfaces;

import ar.edu.unlam.pb2.clases.Accion;
import ar.edu.unlam.pb2.clases.Alarma;
import ar.edu.unlam.pb2.clases.CentralDeAlarmas;
import ar.edu.unlam.pb2.clases.Usuario;
import ar.edu.unlam.pb2.excepciones.CodigoDeConfiguracionIncorrectoException;
import ar.edu.unlam.pb2.excepciones.SensorApagadoException;
import ar.edu.unlam.pb2.excepciones.UsuarioSinPermisoException;

public interface IActivable {

	 public Boolean activarDesactivarAlarma ( Alarma alarma , String codActivacion, Accion accionRealizada , CentralDeAlarmas central , Usuario usuario) throws CodigoDeConfiguracionIncorrectoException, UsuarioSinPermisoException, SensorApagadoException;
	
}
