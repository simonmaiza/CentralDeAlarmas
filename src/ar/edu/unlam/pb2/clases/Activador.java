package ar.edu.unlam.pb2.clases;

import ar.edu.unlam.pb2.excepciones.CodigoDeConfiguracionIncorrectoException;
import ar.edu.unlam.pb2.excepciones.SensorApagadoException;
import ar.edu.unlam.pb2.excepciones.UsuarioSinPermisoException;
import ar.edu.unlam.pb2.interfaces.IActivable;

public class Activador extends Usuario implements IActivable {

	public Activador(Integer dni, String nombre) {
		super(dni, nombre);
	
	}

	@Override
	public Boolean activarDesactivarAlarma(Alarma alarma, String codActivacion, Accion accionRealizada,CentralDeAlarmas central , Usuario usuario) throws CodigoDeConfiguracionIncorrectoException, UsuarioSinPermisoException, SensorApagadoException {
	Alarma alarmaEncontrada = central.buscarAlarmaPorId(alarma.getIdAlarma());
	corroborarCodigoActivacionDesactivacionAlarma(alarmaEncontrada , codActivacion);
	central.corroborarSiElUsuarioEstaPermitido(usuario);
	corroborarQueTodosLosSensoresEstenEncendidos(alarmaEncontrada);
	alarmaEncontrada.setEstadoAlarma(true);
	return alarmaEncontrada.getEstadoAlarma();
	}

	private void corroborarQueTodosLosSensoresEstenEncendidos(Alarma alarmaEncontrada) throws SensorApagadoException {
		for (Sensor it : alarmaEncontrada.getListaDeSensores()) {
			if(it.getEstadoSensor().equals(false)) {
				throw new SensorApagadoException("Al menos uno de los sensores esta desactivado");
			}
		}
		
	}

	private Boolean corroborarCodigoActivacionDesactivacionAlarma(Alarma alarmaEncontrada, String codConfig) throws CodigoDeConfiguracionIncorrectoException {
		if(alarmaEncontrada.getCodConfig().equals(codConfig)) {
			return true;
		}
		throw new CodigoDeConfiguracionIncorrectoException("El codigo de configuracion no es el esperado");
	}

}
