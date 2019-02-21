package com.prueba.canciones.service;

import com.prueba.canciones.dto.CancionesSpotifyDTO;
import com.prueba.canciones.exception.CancionesException;

public interface PlayListService {

	public CancionesSpotifyDTO getPlayListGenero(String genero) throws CancionesException; 
	
}
