package ar.edu.unlam.pb2.excepciones;

public class SensorApagadoException extends Exception {

	private String mensaje;

	public SensorApagadoException(String mensaje) {
		this.mensaje =  mensaje;
	}

}
