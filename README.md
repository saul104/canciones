# canciones
Ejercicio sobre un microservicio que sugiera un playlist de acuerdo a la temperatura de una región.

Este microservicio está creado en Java bajo una arquitectura MVC, se usaron tecnologías como spring boot con maven, 
de cliente rest se utilizó RestTemplate para el manejo de las api de Spotify y Openweathermap. Para un código más reducido se uso Lombok.

Se dejo el servidor Tomcat embebido y la configuración del puerto 8080. En la parte de pruebas se hizo uso de Junit.

# Breve explicación del desarrollo
De acuerdo a la arquitectura MVC se tienen dos puntos de entrada en el controller de canciones (un método con el parámetro de ciudad y otro 
método con los parámetros de latitud y longitud). La comunicación se da con el service de canciones para el manejo principal del negocio, 
aquí se hace uso de otros dos service. TemperaturaService para consumir el api de OpenWeather y conseguir la temperatura de la región indicada
y PlayListService para consumir el api de Spotify y conseguir una lista de canciones de acuerdo a un género musical que le indiquemos.

Como resultado de la comunicación de estos elementos (y algunos otros complementarios) se tendrá un listado de canciones de cierto género
músical de acuerdo a la temperatura de una región indicada o en su defecto se lanzará una excepción en caso de tener un error con los
parámetros de la petición.

# Peticiones de ejemplo
Parámetro -> ciudad
http://localhost:8080/rest/canciones?ciudad=iztapalapa

Resultado
{
"tracks": {
"items": [
           {
              "name": "Desconocidos"
              },
                {
              "name": "Te Vi"
              },
                {
              "name": "Baby Girl (feat. Lalo Ebratt)"
              }
          ],
        }
}


Parámetros -> latitud, longitud
http://localhost:8080/rest/canciones?latitud=37.39&longitud=-122.09

Resultado
{
"tracks": {
"items": [
            {
            "name": "Cello Suite No. 1 in G Major, BWV 1007: I. Prélude"
            },
             {
            "name": "Vivo Por Ella (Vivo Per Lei) - Italian - Spanish Version With Marta Sanchez"
            },
              {
            "name": "Por Ti Volare (Con Te Partiro) - Spanish Version"
            }
          ],
        }
}

# Fuentes de uso de api's
https://openweathermap.org/current
https://developer.spotify.com/documentation/web-api/reference/search/search/		
https://developer.spotify.com/documentation/web-api/reference/search/search/#writing-a-query---guidelines
https://developer.spotify.com/documentation/general/guides/authorization-guide/
