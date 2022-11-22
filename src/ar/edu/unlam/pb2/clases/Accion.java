package ar.edu.unlam.pb2.clases;

import java.util.Date;

public class Accion {

	private Integer idAccion;
	private Alarma alarmaAccionRealizada;
	private Usuario usuarioQueRealizaLaAccion;
	private Date fecha;
	private TipoDeOperacion tipoDeOperacion;
	
	public Accion(Integer idAccion, Alarma alarmaAccionRealizada, Usuario usuarioQueRealizaLaAccion, Date fecha,
			TipoDeOperacion tipoDeOperacion) {
		super();
		this.idAccion = idAccion;
		this.alarmaAccionRealizada = alarmaAccionRealizada;
		this.usuarioQueRealizaLaAccion = usuarioQueRealizaLaAccion;
		this.fecha = fecha;
		this.tipoDeOperacion = tipoDeOperacion;
	}

	public Integer getIdAccion() {
		return idAccion;
	}

	public void setIdAccion(Integer idAccion) {
		this.idAccion = idAccion;
	}

	public Alarma getAlarmaAccionRealizada() {
		return alarmaAccionRealizada;
	}

	public void setAlarmaAccionRealizada(Alarma alarmaAccionRealizada) {
		this.alarmaAccionRealizada = alarmaAccionRealizada;
	}

	public Usuario getUsuarioQueRealizaLaAccion() {
		return usuarioQueRealizaLaAccion;
	}

	public void setUsuarioQueRealizaLaAccion(Usuario usuarioQueRealizaLaAccion) {
		this.usuarioQueRealizaLaAccion = usuarioQueRealizaLaAccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public TipoDeOperacion getTipoDeOperacion() {
		return tipoDeOperacion;
	}

	public void setTipoDeOperacion(TipoDeOperacion tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}
	
	
	
	
}
