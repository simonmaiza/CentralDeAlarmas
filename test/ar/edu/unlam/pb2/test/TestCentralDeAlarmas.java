package ar.edu.unlam.pb2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

import ar.edu.unlam.pb2.clases.Accion;
import ar.edu.unlam.pb2.clases.Activador;
import ar.edu.unlam.pb2.clases.Administrador;
import ar.edu.unlam.pb2.clases.Alarma;
import ar.edu.unlam.pb2.clases.CentralDeAlarmas;
import ar.edu.unlam.pb2.clases.Configurador;
import ar.edu.unlam.pb2.clases.Sensor;
import ar.edu.unlam.pb2.clases.Usuario;
import ar.edu.unlam.pb2.excepciones.AlarmaExistenteException;
import ar.edu.unlam.pb2.excepciones.CodigoDeActivacionDesactivacionIncorrecto;
import ar.edu.unlam.pb2.excepciones.CodigoDeConfiguracionIncorrectoException;
import ar.edu.unlam.pb2.excepciones.SensorApagadoException;
import ar.edu.unlam.pb2.excepciones.SensorDuplicadoException;
import ar.edu.unlam.pb2.excepciones.UsuarioExistenteException;
import ar.edu.unlam.pb2.excepciones.UsuarioSinPermisoException;
import ar.edu.unlam.pb2.interfaces.IConfigurable;

public class TestCentralDeAlarmas {

	@Test
	public void queSePuedaCrearUnaCentralDeAlarmas() {
		
		CentralDeAlarmas central = new CentralDeAlarmas();
		assertNotNull(central);
	}
	
	@Test
	public void queSePuedanCrearLosDistintosTiposDeUsuario() {
		
		Administrador administrador = new Administrador(0001, "Simon");
		Usuario configurador = new Configurador(0002 , "Joaquin");
		Usuario activador = new Activador(0003, "Jose");
		
		assertNotNull(administrador);
		assertNotNull(configurador);
		assertNotNull(activador);
	}
	
	@Test
	public void queSePuedaCrearUnaAlarma() {
		
		Alarma alarma = new Alarma(1 , "123" , "321" , "A", false );
		assertNotNull(alarma);
	}
	
	@Test
	public void queSePuedaAgregarUnaAlarmaEnLaCentral() throws AlarmaExistenteException {
		
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Alarma alarma = new Alarma(1 , "123" , "321" , "A", false );
		Alarma alarma2 = new Alarma(2, "456" , "654" , "B", false);
		
		administrador.agregarAlarmaALaCentral(alarma , central);
		//administrador.agregarAlarmaALaCentral(alarma , central);
		administrador.agregarAlarmaALaCentral(alarma2, central);
	
		Integer valorEsperado = 2;
		Integer valorObtenido = central.obtenerCantidadDeAlarmasEnLaCentral();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queSePuedaAgregarUnUsuarioALaCentral() throws UsuarioExistenteException {
		
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Usuario configurador = new Configurador(0002 , "Joaquin");
		Usuario activador = new Activador(0003, "Jose");
		
		administrador.agregarUsuarioALaCentral(configurador , central);
		//administrador.agregarUsuarioALaCentral(configurador , central);
		administrador.agregarUsuarioALaCentral(activador, central);
		
		Integer valorEsperado = 2;
		Integer valorObtenido = central.obtenerCantidadDeUsuariosEnLaCentral();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queSePuedaAgregarUnUsuarioAUnaAlarma() throws CodigoDeConfiguracionIncorrectoException, UsuarioExistenteException, AlarmaExistenteException {
		
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Usuario configurador = new Configurador(0002 , "Joaquin");
		Alarma alarma = new Alarma(1 , "123" , "321" , "A", false );
		
		administrador.agregarUsuarioALaCentral(configurador , central);
		administrador.agregarAlarmaALaCentral(alarma , central);
		administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , alarma.getCodConfig() , central);
		//administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , alarma.getCodConfig() , central);
		
		Integer valorEsperado = 1;
		Integer valorObtenido = alarma.obtenerCantidadDeUsuariosValidados();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test(expected = CodigoDeConfiguracionIncorrectoException.class)
	public void alAgregarUnUsuarioAUnaAlarmaConCodigoDeConfiguracionDeAlarmaInvalidoSeLanceCodigoAlarmaIncorrectoException() throws UsuarioExistenteException, AlarmaExistenteException, CodigoDeConfiguracionIncorrectoException {
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Usuario configurador = new Configurador(0002 , "Joaquin");
		Alarma alarma = new Alarma(1 , "123" , "321" , "A", false );
		
		administrador.agregarUsuarioALaCentral(configurador , central);
		administrador.agregarAlarmaALaCentral(alarma , central);
		administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , "0" , central);
		//administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , alarma.getCodConfig() , central);
	
	}
	
	@Test
	public void queSePuedaAgrearUnSensorAUnaAlarma() throws CodigoDeConfiguracionIncorrectoException, UsuarioSinPermisoException, SensorDuplicadoException, UsuarioExistenteException, AlarmaExistenteException {
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Alarma alarma = new Alarma(1 , "123" , "321" , "A", false );
		Sensor sensor1 = new Sensor(01 , true);
		Usuario configurador = new Configurador(0002 , "Joaquin");
		
		administrador.agregarUsuarioALaCentral(configurador , central);
		administrador.agregarAlarmaALaCentral(alarma , central);
		administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , alarma.getCodConfig() , central);
		administrador.agregarSensorAUnaAlarma(alarma.getIdAlarma() , alarma.getCodConfig() , sensor1 , configurador.getDni() , central);
		
		Integer valorEsperado = 1;
		Integer valorObtenido = alarma.obtenerCantidadDeSensores();
		
		assertEquals(valorEsperado, valorObtenido);
		
	}
	
	@Test(expected = SensorDuplicadoException.class)
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException() throws CodigoDeConfiguracionIncorrectoException, UsuarioSinPermisoException, SensorDuplicadoException, UsuarioExistenteException, AlarmaExistenteException {
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Alarma alarma = new Alarma(1 , "123" , "321" , "A", false );
		Sensor sensor1 = new Sensor(01 , true);
		Usuario configurador = new Configurador(0002 , "Joaquin");
		
		administrador.agregarUsuarioALaCentral(configurador , central);
		administrador.agregarAlarmaALaCentral(alarma , central);
		administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , alarma.getCodConfig() , central);
		administrador.agregarSensorAUnaAlarma(alarma.getIdAlarma() , alarma.getCodConfig() , sensor1 , configurador.getDni() , central);
		administrador.agregarSensorAUnaAlarma(alarma.getIdAlarma() , alarma.getCodConfig() , sensor1 , configurador.getDni() , central);
		
		Integer valorEsperado = 1;
		Integer valorObtenido = alarma.obtenerCantidadDeSensores();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queSePuedaActivarUnSensorDeAlarma() throws CodigoDeConfiguracionIncorrectoException, CodigoDeActivacionDesactivacionIncorrecto, SensorApagadoException, UsuarioSinPermisoException, SensorDuplicadoException, AlarmaExistenteException, UsuarioExistenteException {
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Alarma alarma = new Alarma(1 , "123" , "321" , "A", false );
		Sensor sensor1 = new Sensor(01 , true);
		Usuario configurador = new Configurador(0002 , "Joaquin");
		
		administrador.agregarUsuarioALaCentral(configurador , central);
		administrador.agregarAlarmaALaCentral(alarma , central);
		administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , alarma.getCodConfig() , central);
		administrador.agregarSensorAUnaAlarma(alarma.getIdAlarma() , alarma.getCodConfig() , sensor1 , configurador.getDni() , central);
		administrador.activarSensorDeAlarma(sensor1.getIdSensor() , alarma.getIdAlarma() , alarma.getCodActivacionDesactivacion() , central , alarma);		
		
		Boolean valorEsperado = true;
		Boolean valorObtenido = sensor1.getEstadoSensor();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queNoSePuedaActivarUnaAlarmaSiHayAlMenosUnSensorDesactivado() throws CodigoDeActivacionDesactivacionIncorrecto, UsuarioSinPermisoException, SensorApagadoException, CodigoDeConfiguracionIncorrectoException, SensorDuplicadoException, UsuarioExistenteException, AlarmaExistenteException {
		CentralDeAlarmas central = new CentralDeAlarmas();
		Administrador administrador = new Administrador(0001, "Simon");
		Alarma alarma = new Alarma(1 , "123" , "321" , "A" , false);
		Sensor sensor1 = new Sensor(01 , true);
		Usuario configurador = new Configurador(0002 , "Joaquin");
		
		administrador.agregarUsuarioALaCentral(configurador , central);
		administrador.agregarAlarmaALaCentral(alarma , central);
		//administrador.agregarUsuarioAUnaAlarma(configurador.getDni() , alarma.getIdAlarma() , alarma.getCodConfig() , central);
		//administrador.agregarSensorAUnaAlarma(alarma.getIdAlarma() , alarma.getCodConfig() , sensor1 , configurador.getDni() , central);
		//administrador.activarSensorDeAlarma(sensor1.getIdSensor() , alarma.getIdAlarma() , alarma.getCodActivacionDesactivacion() , central , alarma);	
		administrador.activarDesactivarAlarma(alarma.getIdAlarma() , alarma.getCodActivacionDesactivacion() , configurador , central);
		
		Boolean valorEsperado = true;
		Boolean valorObtenido = alarma.getEstadoAlarma();
		
		assertEquals(valorEsperado, valorObtenido);
	}
	
	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAcccionesDeTipoConfiguracionOdenadasPorIdDeAccion() {
		CentralDeAlarmas central = new CentralDeAlarmas();
		Alarma alarma = new Alarma(1 , "123" , "321" , "A" , false);
		IConfigurable configurador = new Configurador(0002 , "Joaquin");
		
		Set<Accion> accionesOrdenadas = configurador.obtenerUnaListaDeAccionesOrdenadasPorId(alarma);
		
		for (Accion accion : accionesOrdenadas) {
			System.out.println(accion.getIdAccion());
		}
	}

	
	
}
