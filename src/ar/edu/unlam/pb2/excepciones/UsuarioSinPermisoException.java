package ar.edu.unlam.pb2.excepciones;

public class UsuarioSinPermisoException extends Exception {

	private String mensaje;

	public UsuarioSinPermisoException(String mesanje) {
		this.mensaje = mesanje;
	}

}
