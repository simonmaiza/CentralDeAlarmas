package ar.edu.unlam.pb2.clases;

import java.util.Set;
import java.util.TreeSet;

import ar.edu.unlam.pb2.excepciones.AccionRealizadaException;
import ar.edu.unlam.pb2.excepciones.CodigoDeConfiguracionIncorrectoException;
import ar.edu.unlam.pb2.excepciones.SensorDuplicadoException;
import ar.edu.unlam.pb2.excepciones.UsuarioExistenteException;
import ar.edu.unlam.pb2.interfaces.IConfigurable;

public class Configurador extends Usuario implements IConfigurable{
	private Set<Accion>accionesOrdenadasPorId;

	public Configurador(Integer dni, String nombre) {
		super(dni, nombre);
		this.accionesOrdenadasPorId = new TreeSet<Accion>();

	}


	@Override
	public void agregarAListaDeUsuariosValidos(Integer dniUsuario, Integer idAlarma, String codConfig, CentralDeAlarmas central , Accion accionRealizada) throws CodigoDeConfiguracionIncorrectoException, UsuarioExistenteException, AccionRealizadaException {
		
		TipoDeOperacion tipo = TipoDeOperacion.CONFIGURACION;
		accionRealizada.setTipoDeOperacion(tipo);
		Usuario usuarioEncontrado = central.buscarUsuarioPorId(dniUsuario);
		Alarma alarmaEncontrada = central.buscarAlarmaPorId(idAlarma);
		corroborarCodigoDeConfiguracion (codConfig , alarmaEncontrada);
		alarmaEncontrada.agregarUsuario(usuarioEncontrado);
		agregarAccionRealizada(accionRealizada, alarmaEncontrada);
	}


	private void agregarAccionRealizada(Accion accionRealizada, Alarma alarmaEncontrada)
			throws AccionRealizadaException {
		if(accionRealizada.getTipoDeOperacion().equals(TipoDeOperacion.CONFIGURACION)){
			alarmaEncontrada.agregarAccion(accionRealizada);
		}
	}


	private Boolean corroborarCodigoDeConfiguracion(String codConfig , Alarma alarmaEncontrada) throws CodigoDeConfiguracionIncorrectoException {
		if(alarmaEncontrada.getCodConfig().equals(codConfig)) {
			return true;
		}
		throw new CodigoDeConfiguracionIncorrectoException("El codigo de configuracion no es el esperado");
	}


	@Override
	public void agregarSensorAUnaAlarma(Integer idAlarma, String codConfig, Sensor sensor, Accion accionRealizada , CentralDeAlarmas central)throws SensorDuplicadoException, CodigoDeConfiguracionIncorrectoException, AccionRealizadaException {
		
		TipoDeOperacion tipo = TipoDeOperacion.CONFIGURACION;
		accionRealizada.setTipoDeOperacion(tipo);
		Alarma alarmaEncontrada = central.buscarAlarmaPorId(idAlarma);
		corroborarCodigoDeConfiguracion(codConfig, alarmaEncontrada);
		alarmaEncontrada.agregarSensor(sensor);
		agregarAccionRealizada(accionRealizada, alarmaEncontrada);
		
	}


	@Override
	public void activarSensor(Integer idSensor, Integer idAlarma, String codConfig, CentralDeAlarmas central, Accion accionRealizada) throws CodigoDeConfiguracionIncorrectoException, AccionRealizadaException {
		Alarma alarmaEncontrada = central.buscarAlarmaPorId(idAlarma);
		corroborarCodigoDeConfiguracion(codConfig, alarmaEncontrada);
		Sensor sensorEncontrado = alarmaEncontrada.buscarSensorPorId(idSensor);
		sensorEncontrado.setEstadoSensor(true);
		agregarAccionRealizada(accionRealizada, alarmaEncontrada);
		
	}


	@Override
	
	public Set<Accion> obtenerUnaListaDeAccionesOrdenadasPorId(Alarma alarma) {
		for (Accion accion : alarma.getAccionesRealizadas()) {
			if(accion.getTipoDeOperacion().equals(TipoDeOperacion.CONFIGURACION)) {
				accionesOrdenadasPorId.add(accion);
			}
		}
		accionesOrdenadasPorId = new TreeSet<>(new OrdenAccionPorId());
		accionesOrdenadasPorId.addAll(alarma.getAccionesRealizadas());
		return accionesOrdenadasPorId;
	
	
		
	}


	
	
	

}
