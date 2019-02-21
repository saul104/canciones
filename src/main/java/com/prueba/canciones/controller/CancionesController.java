/***********************************************************
 *    Ejercicio sobre sugerir un playlist de acuerdo       *
 *    a la temperatura de una región. 					   *
 *                          							   *
 ***********************************************************/

package com.prueba.canciones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.canciones.dto.CancionesSpotifyDTO;
import com.prueba.canciones.exception.CancionesException;
import com.prueba.canciones.service.CancionesService;

@RestController
@RequestMapping(value="/rest/canciones")
public class CancionesController {
	
	@Autowired
	private CancionesService cancionesService;
	
	private final Logger logger = LoggerFactory.getLogger(CancionesController.class);
		
    /**
     * Método que regresa una lista sugerida de canciones dada una ciudad
     * @param ciudad
     * @return objeto que contiene una lista de canciones
     * @throws CancionesException 
     */
    @GetMapping(params = { "ciudad" } )
    public CancionesSpotifyDTO getCanciones(@RequestParam("ciudad") String ciudad) throws CancionesException{	
    	//Ej. -> http://localhost:8080/rest/canciones?ciudad=iztapalapa
    	
    	logger.info("Método con parámetro de la ciudad en la petición");	   	
    	return cancionesService.getCanciones(ciudad);
    }
    
    
    /**
     * Método que regresa una lista sugerida de canciones dada las coordenadas de una ubicación
     * @param latitud
     * @param longitud
     * @return objeto que contiene una lista de canciones
     * @throws CancionesException 
     */
    @GetMapping(params = { "latitud", "longitud" } )
    public CancionesSpotifyDTO getCanciones(@RequestParam("latitud") String latitud, @RequestParam("longitud") String longitud) throws CancionesException{	
    	//Ej. -> http://localhost:8080/rest/canciones?latitud=37.39&longitud=-122.09
    	
    	logger.info("Método con parámetros de coordenadas en la petición");
    	return cancionesService.getCanciones(latitud, longitud);
    }
    
}
