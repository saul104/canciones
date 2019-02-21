package com.prueba.canciones.util;


public class CancionesValidation {
	
	public static boolean isEmptyOrNull(Object... objects){

        for(Object object : objects){
            if(object == null || object.toString().equals("")){
                return true;
            }
        }
        return false;
    }
	
	
    public static boolean isNumberLatitudLongitud(String latitud, String longitud)  {
        try {
        	Float.parseFloat(latitud);
        	Float.parseFloat(longitud);
        	return true;
        }catch(Exception e) {
        	//throw new CancionesException("Se deben ingresar números válidos como párametros");
        	return false;
        }
    }
	
}
