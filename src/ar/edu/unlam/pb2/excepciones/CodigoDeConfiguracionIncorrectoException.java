package ar.edu.unlam.pb2.excepciones;

public class CodigoDeConfiguracionIncorrectoException extends Exception {

	private String mensaje;

	public CodigoDeConfiguracionIncorrectoException(String mensaje) {
		this.mensaje = mensaje;
	}

}
