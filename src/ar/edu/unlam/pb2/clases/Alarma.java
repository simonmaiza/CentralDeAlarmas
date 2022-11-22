package ar.edu.unlam.pb2.clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.pb2.excepciones.AccionRealizadaException;
import ar.edu.unlam.pb2.excepciones.SensorDuplicadoException;
import ar.edu.unlam.pb2.excepciones.UsuarioExistenteException;

public class Alarma {

	private Integer idAlarma;
	private String codActivacionDesactivacion;
	private String codConfig;
	private String nombre;
	private Boolean estadoAlarma;
	private Set<Usuario>usuariosValidos;
	private List<Accion>accionesRealizadas;
	private Set<Sensor>listaDeSensores;
	
	
	public Alarma(Integer idAlarma, String codActivacionDesactivacion, String codConfig, String nombre, Boolean estadoAlarma) {
		this.idAlarma = idAlarma;
		this.codActivacionDesactivacion = codActivacionDesactivacion;
		this.codConfig = codConfig;
		this.nombre = nombre;
		this.estadoAlarma = estadoAlarma;
		this.usuariosValidos = new HashSet<Usuario>();
		this.accionesRealizadas = new ArrayList<Accion>();
		this.listaDeSensores = new HashSet<Sensor>();
	}
	

	public Boolean getEstadoAlarma() {
		return estadoAlarma;
	}


	public void setEstadoAlarma(Boolean estadoAlarma) {
		this.estadoAlarma = estadoAlarma;
	}


	public Integer getIdAlarma() {
		return idAlarma;
	}

	public void setIdAlarma(Integer idAlarma) {
		this.idAlarma = idAlarma;
	}

	public String getCodActivacionDesactivacion() {
		return codActivacionDesactivacion;
	}

	public void setCodActivacionDesactivacion(String codActivacionDesactivacion) {
		this.codActivacionDesactivacion = codActivacionDesactivacion;
	}

	public String getCodConfig() {
		return codConfig;
	}

	public void setCodConfig(String codConfig) {
		this.codConfig = codConfig;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Usuario> getUsuariosValidos() {
		return usuariosValidos;
	}

	public void setUsuariosValidos(Set<Usuario> usuariosValidos) {
		this.usuariosValidos = usuariosValidos;
	}

	public List<Accion> getAccionesRealizadas() {
		return accionesRealizadas;
	}

	public void setAccionesRealizadas(List<Accion> accionesRealizadas) {
		this.accionesRealizadas = accionesRealizadas;
	}

	public Set<Sensor> getListaDeSensores() {
		return listaDeSensores;
	}

	public void setListaDeSensores(Set<Sensor> listaDeSensores) {
		this.listaDeSensores = listaDeSensores;
	}

	public void agregarUsuario(Usuario usuarioEncontrado) throws UsuarioExistenteException {
		if(usuariosValidos.contains(usuarioEncontrado)) {
			throw new UsuarioExistenteException("El usuario que desea habilitar a la alarma , ya se encuentra habilitado");
		}
		this.usuariosValidos.add(usuarioEncontrado);		
	}

	public Integer obtenerCantidadDeUsuariosValidados() {
		return this.usuariosValidos.size();
	}

	public void agregarSensor(Sensor sensor1) throws SensorDuplicadoException {
		if(listaDeSensores.contains(sensor1)) {
			throw new SensorDuplicadoException("El sensor que desea agregar ya se encuentra en la alarma");
		}
		this.listaDeSensores.add(sensor1);
		
	}

	public Integer obtenerCantidadDeSensores() {
		return this.listaDeSensores.size();
	}
	
	public Sensor buscarSensorPorId(Integer idSensor) {
		for (Sensor sensor : listaDeSensores) {
			if(sensor.getIdSensor().equals(idSensor)) {
				return sensor;
			}
		}
		return null;
	}


	public void agregarAccion(Accion accionRealizada) throws AccionRealizadaException {
		if(accionesRealizadas.contains(accionRealizada)) {
			throw new AccionRealizadaException();
		}
		this.accionesRealizadas.add(accionRealizada);
		
	}



}
