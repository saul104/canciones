package com.prueba.canciones.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.prueba.canciones.dto.TemperaturaDTO;
import com.prueba.canciones.exception.CancionesException;
import com.prueba.canciones.service.TemperaturaService;
import com.prueba.canciones.util.CancionesValidation;

@Service
public class TemperaturaServiceImpl implements TemperaturaService{

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${openWeather.appId}")
	private String appIdWeatherMap;
	
	@Value("${openWeather.url}")
	private String urlWeatherMap;

	private final Logger logger = LoggerFactory.getLogger(TemperaturaServiceImpl.class);
	
	
	
	@Override
	public Float getTemperaturaPorNombreCiudad(String ciudad) throws CancionesException  {
		//Info --> https://openweathermap.org/current
		
		if(CancionesValidation.isEmptyOrNull(ciudad)){
			throw new CancionesException("Ciudad no puede ser un parámetro vacío");
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlWeatherMap)
                .queryParam("q", ciudad)              
                .queryParam("APPID", appIdWeatherMap)
                .queryParam("units", "metric");
		
		try {
			ResponseEntity<TemperaturaDTO> temperatura = restTemplate.getForEntity(builder.build().toUriString(), TemperaturaDTO.class);
		
			if(temperatura.getStatusCodeValue() == HttpStatus.OK.value()) {
				logger.info("La temperatura actual para la ciudad "+ ciudad+" es de "+ temperatura.getBody().getMain().get("temp"));
				return temperatura.getBody().getMain().get("temp");
			}else {
				throw new CancionesException("Error en la ciudad solicitada: "+ ciudad);

			}
		}catch(Exception e) {
			throw new CancionesException("Error en la ciudad solicitada: "+ ciudad);
		}
		
	}

	
	@Override
	public Float getTemperaturaPorLatitudLongitud(String latitud, String longitud) throws CancionesException {
		
		if(CancionesValidation.isEmptyOrNull(latitud, longitud)){
			throw new CancionesException("Las coordenadas no pueden ser vacías");
		}
				
		if(!CancionesValidation.isNumberLatitudLongitud(latitud, longitud)){
			throw new CancionesException("Error en las coordenadas indicadas");
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urlWeatherMap)
                .queryParam("lat", latitud)
                .queryParam("lon", longitud)              
                .queryParam("APPID", appIdWeatherMap)
                .queryParam("units", "metric");
		
		try {
			ResponseEntity<TemperaturaDTO> temperatura = restTemplate.getForEntity(builder.build().toUriString(), TemperaturaDTO.class);
			
				if(temperatura.getStatusCodeValue() == HttpStatus.OK.value()) {
					logger.info("La temperatura actual en las coordenadas indicadas es "+ temperatura.getBody().getMain().get("temp"));
					 return temperatura.getBody().getMain().get("temp");
				}else {
					throw new CancionesException("Error en las coordenadas solicitadas");
				}
			}catch(Exception e) {
				throw new CancionesException("Error en la coordenadas solicitadas");
			}		
	}
	
	
}
