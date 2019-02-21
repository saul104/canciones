package com.prueba.canciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CancionesSpotifyDTO {
	
	@JsonProperty("tracks")
	private TrackDTO tracks;
	
}
