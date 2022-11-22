package ar.edu.unlam.pb2.interfaces;

import java.util.Set;

import ar.edu.unlam.pb2.clases.Accion;
import ar.edu.unlam.pb2.clases.Alarma;
import ar.edu.unlam.pb2.clases.CentralDeAlarmas;
import ar.edu.unlam.pb2.clases.Sensor;
import ar.edu.unlam.pb2.clases.TipoDeOperacion;
import ar.edu.unlam.pb2.excepciones.AccionRealizadaException;
import ar.edu.unlam.pb2.excepciones.CodigoDeConfiguracionIncorrectoException;
import ar.edu.unlam.pb2.excepciones.SensorDuplicadoException;
import ar.edu.unlam.pb2.excepciones.UsuarioExistenteException;

public interface IConfigurable {
	
	public void agregarAListaDeUsuariosValidos(Integer dniUsuario , Integer idAlarma , String codConfig , CentralDeAlarmas central , Accion accionRealizada) throws CodigoDeConfiguracionIncorrectoException, UsuarioExistenteException, AccionRealizadaException ;
		
	public void agregarSensorAUnaAlarma(Integer idAlarma , String codConfig , Sensor sensor , Accion accionRealizada , CentralDeAlarmas central)throws SensorDuplicadoException, CodigoDeConfiguracionIncorrectoException, AccionRealizadaException;
	
	public void activarSensor(Integer idSensor , Integer idAlarma , String codConfig , CentralDeAlarmas central , Accion accionRealizada) throws CodigoDeConfiguracionIncorrectoException, AccionRealizadaException;

	public Set<Accion> obtenerUnaListaDeAccionesOrdenadasPorId(Alarma alarma);
}
