package com.prueba.canciones.dto;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperaturaDTO {

	@JsonProperty("main")
	private Map<String, Float> main;
	
}
