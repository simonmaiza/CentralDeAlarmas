package ar.edu.unlam.pb2.excepciones;

public class UsuarioExistenteException extends Exception {

	private String mensaje;

	public UsuarioExistenteException(String mensaje) {
	this.mensaje = mensaje;
	}

}
