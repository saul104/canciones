package com.prueba.canciones.service;

import com.prueba.canciones.exception.CancionesException;

public interface TemperaturaService {

	public Float getTemperaturaPorNombreCiudad(String ciudad) throws CancionesException;	
	public Float getTemperaturaPorLatitudLongitud(String latitud, String longitud) throws CancionesException;

}
