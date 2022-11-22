package ar.edu.unlam.pb2.clases;

public class Sensor {

	private Integer idSensor;
	private Boolean estadoSensor;
	
	public Sensor(Integer idSensor, Boolean estadoSensor) {
		super();
		this.idSensor = idSensor;
		this.estadoSensor = estadoSensor;
	}

	public Integer getIdSensor() {
		return idSensor;
	}

	public void setIdSensor(Integer idSensor) {
		this.idSensor = idSensor;
	}

	public Boolean getEstadoSensor() {
		return estadoSensor;
	}

	public void setEstadoSensor(Boolean estadoSensor) {
		this.estadoSensor = estadoSensor;
	}
	
	
	
}
