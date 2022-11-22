package ar.edu.unlam.pb2.clases;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unlam.pb2.excepciones.AlarmaExistenteException;
import ar.edu.unlam.pb2.excepciones.UsuarioExistenteException;
import ar.edu.unlam.pb2.excepciones.UsuarioSinPermisoException;

public class CentralDeAlarmas {
	
	private Set<Alarma>listaDeAlarmas;
	private Set<Usuario>listaDeUsuarios;

	public CentralDeAlarmas() {
		this.listaDeAlarmas = new HashSet<Alarma>();
		this.listaDeUsuarios = new HashSet<Usuario>();
	}
	
	public Set<Usuario> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(Set<Usuario> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public Set<Alarma> getListaDeAlarmas() {
		return listaDeAlarmas;
	}

	public void setListaDeAlarmas(Set<Alarma> listaDeAlarmas) {
		this.listaDeAlarmas = listaDeAlarmas;
	}
	

	public void agregarAlarma(Alarma alarma) throws AlarmaExistenteException {
		if(listaDeAlarmas.contains(alarma)) {
			throw new AlarmaExistenteException("La alarma que quiere agregar ya esta en la lista");
		}
		this.listaDeAlarmas.add(alarma);
	}

	public Integer obtenerCantidadDeAlarmasEnLaCentral() {
		return this.listaDeAlarmas.size();
	}

	public void agregarUsuario(Usuario usuario) throws UsuarioExistenteException {
		if(listaDeUsuarios.contains(usuario)) {
			throw new UsuarioExistenteException("El usuario que quiere agregar ya esta en la lista");
		}
		this.listaDeUsuarios.add(usuario);
		
	}

	public Integer obtenerCantidadDeUsuariosEnLaCentral() {
		return this.listaDeUsuarios.size();
	}

	public Usuario buscarUsuarioPorId(Integer dniUsuarioAAgregar) {
		for (Usuario usuario : listaDeUsuarios) {
			if(usuario.getDni().equals(dniUsuarioAAgregar)) {
				return usuario;
			}
		}
		return null;
	}

	public Alarma buscarAlarmaPorId(Integer idAlarma) {
		for (Alarma alarma : listaDeAlarmas) {
			if(alarma.getIdAlarma().equals(idAlarma)) {
				return alarma;
			}
		}
		return null;
	}

	public Boolean corroborarSiElUsuarioEstaPermitido(Usuario usuarioEncontrado) throws UsuarioSinPermisoException {
		if(listaDeUsuarios.contains(usuarioEncontrado)) {
			return true;
		}
		throw new UsuarioSinPermisoException("Este usuario no puedo agregar sensores"); 
	}

	
	
	
	
	
	
	
	
	
}
