# TrilateracionTest
Test implementando trilateración de coordenadas usando Spring Boot

Resuelve la formulación al test de trilateración espacial utilizando una implementacion del marco de trabajo Spring boot.

Para generar pruebas en el sistema se deben seguir las siguientes indicaciones:

Nivel (2): Por medio de PostMan generar una petición con la url: http://localhost:8080/topsecret 
Tipo de método: POST.
En body request enviar como parámetro en formato JSON la información formulada en la problemática con la siguiente estructura:
{
  "satellites": [
    {
      "name": "kenobi",
      "distance": 10,
      "message": ["","","la","casa", ""]
    },
    {
      "name": "skywalker",
      "distance": 115.5,
      "message": ["vamos","a","","",""]
    },
    {
      "name": "solo",
      "distance": 142.7,
      "message": ["vamos","","","","roja"]
    }
  ]
}
La informacion de cada arreglo como nombres distancias y el mensaje pueden diferir del ejemplo, pero manteniendo la misma estructura y formato.
En el caso del arreglo del mensaje, se debe cumplir con que cada mensaje tiene un espacio de 5 palabras, sean vacias o no. Esto ultimo para cumplir con el criterio de ordenamiento del mensaje en la respuesta del servicio.

Nivel (3) POST: Por medio de PostMan generar una petición con la url: http://localhost:8080/topsecret_split/NOMBRESATELITE
siendo NOMBRESATELITE el nombre del satélite formulados en el problema (kenobi, skywalker o solo).
Tipo de método: POST.
En body request enviar como parámetro en formato JSON la información formulada en la problemática con la siguiente estructura:
{      "distance": 142.7,
      "message": ["este","","un","",""]
}
En esta petición se debe enviar la información de cada satélite para que luego la respuesta del servicio GET tenga éxito y pueda retornar el mensaje ordenado sea cual sea este último.
Para enviar la información de cada satélite solo se debe cambiar en la URL el campo NOMBRESATELITE y asignarle en el JSON la información deseada (distance y message)  a cada satélite.

Nivel (3) GET: Por medio de PostMan generar una petición con la url: http://localhost:8080/topsecret_split
Tipo de método: GET
En este servicio no se envía nada como parámetro y debe retornar el mensaje ordenado junto con el resto de información requerida en la formulación del problema. En caso de que no se envié la información completa de los 3 satélites o el mensaje no se pueda ordenar, el servicio responderá con un  404.
