package ar.edu.unlam.pb2.clases;

import ar.edu.unlam.pb2.excepciones.AlarmaExistenteException;
import ar.edu.unlam.pb2.excepciones.CodigoDeActivacionDesactivacionIncorrecto;
import ar.edu.unlam.pb2.excepciones.CodigoDeConfiguracionIncorrectoException;
import ar.edu.unlam.pb2.excepciones.SensorApagadoException;
import ar.edu.unlam.pb2.excepciones.SensorDuplicadoException;
import ar.edu.unlam.pb2.excepciones.UsuarioExistenteException;
import ar.edu.unlam.pb2.excepciones.UsuarioSinPermisoException;

public class Administrador  {
	private Integer dni;
	private String nombre;

	public Administrador(Integer dni, String nombre) {
		this.dni = dni;
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean agregarAlarmaALaCentral(Alarma alarma, CentralDeAlarmas central) throws AlarmaExistenteException {
		central.agregarAlarma(alarma);
		return true;
		
	}

	public Boolean agregarUsuarioALaCentral(Usuario usuario, CentralDeAlarmas central) throws UsuarioExistenteException {
		central.agregarUsuario(usuario);
		return true;
		
	}

	public void agregarUsuarioAUnaAlarma(Integer dniUsuarioAAgregar, Integer idAlarma, String codConfig, CentralDeAlarmas central) throws CodigoDeConfiguracionIncorrectoException, UsuarioExistenteException {
		Usuario usuarioEncontrado = central.buscarUsuarioPorId(dniUsuarioAAgregar);
		Alarma alarmaEncontrada = central.buscarAlarmaPorId(idAlarma);
		corroborarCodigoDeConfiguracion(codConfig, alarmaEncontrada);
		alarmaEncontrada.agregarUsuario(usuarioEncontrado);
	}

	private Boolean corroborarCodigoDeConfiguracion(String codConfig, Alarma alarmaEncontrada) throws CodigoDeConfiguracionIncorrectoException {
		if(alarmaEncontrada.getCodConfig().equals(codConfig)) {
			return true;
		}
		throw new CodigoDeConfiguracionIncorrectoException("El codigo de configuracion no es el esperado");
		
	}

	public void agregarSensorAUnaAlarma(Integer idAlarma, String codConfig, Sensor sensor1, Integer dniUsuario, CentralDeAlarmas central) throws CodigoDeConfiguracionIncorrectoException, UsuarioSinPermisoException, SensorDuplicadoException {
		Usuario usuarioEncontrado = central.buscarUsuarioPorId(dniUsuario);
		central.corroborarSiElUsuarioEstaPermitido(usuarioEncontrado);
		Alarma alarmaEncontrada = central.buscarAlarmaPorId(idAlarma);
		corroborarCodigoDeConfiguracion(codConfig, alarmaEncontrada);
		alarmaEncontrada.agregarSensor(sensor1);
	}

	public Boolean activarSensorDeAlarma(Integer idSensor, Integer idAlarma, String codActivacionDesactivacion, CentralDeAlarmas central, Alarma alarma) throws CodigoDeConfiguracionIncorrectoException, CodigoDeActivacionDesactivacionIncorrecto, SensorApagadoException {
		Alarma alarmaEncontrada = central.buscarAlarmaPorId(idAlarma);
		corroborarCodigoDeActivacionDesactivacion(codActivacionDesactivacion , alarmaEncontrada);
		Sensor sensorEncontrado = alarma.buscarSensorPorId(idSensor);
		sensorEncontrado.setEstadoSensor(true);
		return true;
	}


	private Boolean corroborarCodigoDeActivacionDesactivacion(String codActivacionDesactivacion, Alarma alarmaEncontrada) throws CodigoDeActivacionDesactivacionIncorrecto {
		if(alarmaEncontrada.getCodActivacionDesactivacion().equals(codActivacionDesactivacion)) {
			return true;
		}
		throw new CodigoDeActivacionDesactivacionIncorrecto();
		
	}

	public Boolean activarDesactivarAlarma(Integer idAlarma, String codActivacionDesactivacion, Usuario configurador, CentralDeAlarmas central) throws CodigoDeActivacionDesactivacionIncorrecto, UsuarioSinPermisoException, SensorApagadoException {
		Alarma alarmaEncontrada = central.buscarAlarmaPorId(idAlarma);
		corroborarCodigoDeActivacionDesactivacion(codActivacionDesactivacion, alarmaEncontrada);
		central.corroborarSiElUsuarioEstaPermitido(configurador);
		corroborarQueTodosLosSensoresEstenEncendidos(alarmaEncontrada);
		alarmaEncontrada.setEstadoAlarma(true);
		return alarmaEncontrada.getEstadoAlarma();
	}
	
	private void corroborarQueTodosLosSensoresEstenEncendidos(Alarma alarmaEncontrada) throws SensorApagadoException {
		for (Sensor it : alarmaEncontrada.getListaDeSensores()) {
			if(it.getEstadoSensor().equals(false)) {
				throw new SensorApagadoException("Al menos uno de los sensores esta apagado");
			}
		}
		
	}

}
