package ar.edu.unlam.pb2.excepciones;

public class SensorDuplicadoException extends Exception {

	private String mensaje;

	public SensorDuplicadoException(String mensaje) {
		this.mensaje = mensaje;
	}

}
