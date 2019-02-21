package com.prueba.canciones.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.canciones.dto.CancionesSpotifyDTO;
import com.prueba.canciones.exception.CancionesException;
import com.prueba.canciones.service.CancionesService;
import com.prueba.canciones.service.PlayListService;
import com.prueba.canciones.service.TemperaturaService;
import com.prueba.canciones.util.CancionesConstants;


@Service
public class CancionesServiceImpl implements CancionesService {
	
	@Autowired
	private TemperaturaService temperaturaService;
	
	@Autowired
	private PlayListService playListService;
	
	private final Logger logger = LoggerFactory.getLogger(CancionesServiceImpl.class);

	
	@Override
	public CancionesSpotifyDTO getCanciones(String ciudad) throws CancionesException {	
		String generoMusical = sugerirGeneroPorTemperatura(temperaturaService.getTemperaturaPorNombreCiudad(ciudad));
		logger.info("Genero sugerido para la temperatura actual: "+ generoMusical);
		return playListService.getPlayListGenero(generoMusical);
	}

	
	
	@Override
	public CancionesSpotifyDTO getCanciones(String latitud, String longitud) throws CancionesException {	
		String generoMusical = sugerirGeneroPorTemperatura(temperaturaService.getTemperaturaPorLatitudLongitud(latitud, longitud));
		logger.info("Genero sugerido para la temperatura actual: "+ generoMusical);
		return playListService.getPlayListGenero(generoMusical);
	}


	
	private String sugerirGeneroPorTemperatura(Float temperatura){
		if(temperatura > 30) {
			return CancionesConstants.GENERO_CALUROSO;
		}
		
		if(temperatura >= 15) {
			return CancionesConstants.GENERO_CALIDO;
		}
		
		if(temperatura >= 10) {
			return CancionesConstants.GENERO_TEMPLADO;
		}else {
			return CancionesConstants.GENERO_FRIO;
		}
		
	}

}
