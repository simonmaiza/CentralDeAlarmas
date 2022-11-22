package ar.edu.unlam.pb2.excepciones;

public class AlarmaExistenteException extends Exception {

	private String mensaje;

	public AlarmaExistenteException(String mensaje) {
		this.mensaje = mensaje;
	}

}
