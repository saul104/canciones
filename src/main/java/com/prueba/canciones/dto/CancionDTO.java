package com.prueba.canciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CancionDTO {

	@JsonProperty("name")
	String nombre;
	
}
