package com.rebelion.artifacttest.controller;

import com.rebelion.artifacttest.model.CoordenadasDTO;
import com.rebelion.artifacttest.model.NaveDTO;
import com.rebelion.artifacttest.model.Nivel3DTO;
import com.rebelion.artifacttest.model.PosicionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;
import com.rebelion.artifacttest.servicios.ImensajeService;


import java.util.List;
import java.util.Map;


@RestController
public class MensajeRestController {

    private static final Logger logger = LoggerFactory.getLogger(MensajeRestController.class);

    @Autowired
    private ImensajeService imensajeService;

    /**
     * Servicio para obtener las coordenadas y el mensaje del emisor (Nivel 2)
     * @param naveDTO Informacion correspondiente al mensaje y ubicacion del satelite / nave
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    @PostMapping(path = "topsecret")
    public PosicionDTO obtenerListaInformacion(@RequestBody Map<String, List<NaveDTO>> naveDTO){
        logger.debug("MensajeRestController::obtenerListaInformacion()");
        PosicionDTO posicionDTO = new PosicionDTO();

        try {
                CoordenadasDTO posiciones = imensajeService.asignarCoordenadasNave(naveDTO.get("satellites"));
                if(posiciones != null){
                    posicionDTO.setCoordenadasDTO(posiciones);
                    List<String[]> mensajeSatelites = imensajeService.obtenerMensajes(naveDTO.get("satellites"));
                    String mensaje = imensajeService.organizarMensaje(mensajeSatelites);
                    posicionDTO.setMensaje(mensaje);
                    return posicionDTO;
                }
        } catch (Exception ee){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Error.", ee);
    }
        return posicionDTO;
    }



    /**
     * Servicio para guardar en la variables locales la informacion de cada satelite
     * @param nivel3DTO Informacion correspondiente al JSON que recibimos.
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    @PostMapping(path = "topsecret_split/{satellite_name}")
    public NaveDTO guardarInformacionSatelite(@RequestBody Nivel3DTO nivel3DTO, @PathVariable("satellite_name") String nombreSatelite){
        logger.debug("MensajeRestController::guardarInformacionSatelite()");
        try {

        NaveDTO naveDTO = imensajeService.guardarInfoSatelite(nivel3DTO,nombreSatelite);
        return naveDTO;

        } catch (Exception ee){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Error.", ee);
        }
    }


    /**
     * Servicio para consultar la informacion de cada satelite y el mensaje del emisor (Nivel 3)
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    @GetMapping(path = "topsecret_split")
    public PosicionDTO consultarInformacionSatelite(){
        logger.debug("MensajeRestController::consultarInformacionSatelite()");
        PosicionDTO posicion = new PosicionDTO();
        try {
            posicion = imensajeService.consultarInformacionSatelite();
            if(posicion != null ){
                return posicion;
            }else{
                posicion.getMensaje();
            }

        } catch (Exception ee){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Error.", ee);
        }
        return posicion;
    }



}
