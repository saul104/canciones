package com.prueba.canciones.service;

import com.prueba.canciones.dto.CancionesSpotifyDTO;
import com.prueba.canciones.exception.CancionesException;

public interface CancionesService {

	public CancionesSpotifyDTO getCanciones(String ciudad) throws CancionesException;
	public CancionesSpotifyDTO getCanciones(String latitud, String longitud) throws CancionesException;
	
}
