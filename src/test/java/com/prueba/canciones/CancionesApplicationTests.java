package com.prueba.canciones;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.prueba.canciones.exception.CancionesException;
import com.prueba.canciones.service.CancionesService;
import com.prueba.canciones.service.PlayListService;
import com.prueba.canciones.service.TemperaturaService;
import com.prueba.canciones.util.CancionesConstants;
import com.prueba.canciones.util.CancionesValidation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CancionesApplicationTests {

	@Autowired
	private TemperaturaService temperaturaService;
	
	@Autowired
	private PlayListService playListService;
	
	@Autowired
	private CancionesService cancionesService;
	
	@Test
	public void testCancionesValidation()  {
		assertFalse(CancionesValidation.isNumberLatitudLongitud("palabra", "palabra"));
		assertTrue(CancionesValidation.isNumberLatitudLongitud("-20", "10.5"));
		
		assertTrue(CancionesValidation.isEmptyOrNull("","","",null,"2"));
		assertFalse(CancionesValidation.isEmptyOrNull("Iztapalapa"));
	}	
	
	@Test
	public void testTemperaturaService() throws CancionesException {
		assertNotNull(temperaturaService.getTemperaturaPorLatitudLongitud("37.39", "-122.09"));
		assertNotNull(temperaturaService.getTemperaturaPorNombreCiudad("iztapalapa"));    
	}
	
	@Test
	public void testPlayListService() throws CancionesException{
		assertNotNull(playListService.getPlayListGenero(CancionesConstants.GENERO_CALUROSO));
		
	}
	
	@Test (expected = CancionesException.class)
	public void testExceptionCancionesService() throws CancionesException{
		cancionesService.getCanciones("CiudadFalsa");
	}
	
	@Test
	public void testaCancionesService() throws CancionesException{
		assertNotNull(cancionesService.getCanciones("iztapalapa"));
		assertNotNull(cancionesService.getCanciones("37.39", "-122.09"));
	}
}

