package com.prueba.canciones.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TrackDTO {

	@JsonProperty("items")
	private List<CancionDTO> items;
}
