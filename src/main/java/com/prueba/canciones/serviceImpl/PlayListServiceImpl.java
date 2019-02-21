package com.prueba.canciones.serviceImpl;

import java.util.Base64;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.prueba.canciones.dto.CancionesSpotifyDTO;
import com.prueba.canciones.exception.CancionesException;
import com.prueba.canciones.service.PlayListService;


@Service
public class PlayListServiceImpl implements PlayListService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${spotify.account.url}")
	private String spotifyAccountUrl;
	
	@Value("${spotify.api.url}")
	private String spotifyApiUrlSearch;
	
	@Value("${spotify.authorization.clientId}")
	private String spotifyClientId;
	
	@Value("${spotify.authorization.clientSecret}")
	private String spotifyClientSecret;
	
	private final Logger logger = LoggerFactory.getLogger(PlayListServiceImpl.class);
	
	
	@Override
	public CancionesSpotifyDTO getPlayListGenero(String genero) throws CancionesException {
		//Info Search https://developer.spotify.com/documentation/web-api/reference/search/search/		
		//https://developer.spotify.com/documentation/web-api/reference/search/search/#writing-a-query---guidelines
		ResponseEntity<CancionesSpotifyDTO> entity  = null;
		
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", getAuthorization());
        
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(spotifyApiUrlSearch)
                .queryParam("q", "genre:"+genero)
                .queryParam("type", "track")
                .queryParam("market", "MX")
                .queryParam("limit", 12);
        
        try {
	        entity = restTemplate.exchange(builder.build().toUriString(), HttpMethod.GET, httpEntity, CancionesSpotifyDTO.class);			
	        
			if(entity.getStatusCodeValue() == HttpStatus.OK.value()) {
				logger.info("Se ha consultado la api de Spotify para el genero "+ genero);
			}
        }catch(Exception e) {
        	e.printStackTrace();
        	throw new CancionesException("Error al obtener el playlist sugerido en Spotify");
        }		
		
        return entity.getBody();
	}

	
	   private String getAuthorization() throws CancionesException {
		   	//Info authorization --> https://developer.spotify.com/documentation/general/guides/authorization-guide/
		    ResponseEntity<Map> entity;
		   
		   	String client = spotifyClientId+":"+spotifyClientSecret;
	        String encodedAuthorization = Base64.getEncoder().encodeToString(client.getBytes());
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Authorization", "Basic " + encodedAuthorization);
	        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	        HttpEntity<String> httpEntity = new HttpEntity<>("grant_type=client_credentials", headers);
	        
	        try {
		        entity = restTemplate.exchange(spotifyAccountUrl, HttpMethod.POST, httpEntity, Map.class);
	
		        if(entity.getStatusCodeValue() == HttpStatus.OK.value()) {
		            String token = String.valueOf(entity.getBody().get("access_token"));
		            //logger.info("token: "+ token);
		            return "Bearer " + token;
		        }

	        }catch(Exception e) {
	        	throw new CancionesException("Error al ingresar datos de registro Spotify");
	        }
	        
	        return null;
	    }
	
}
