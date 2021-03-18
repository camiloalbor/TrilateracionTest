package com.rebelion.artifacttest.model;

import java.io.Serializable;

/**
 * DTO para representar la respuesta del servicio
 * @author Camilo.Albor
 * @since 18/03/2021
 */
public class PosicionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private CoordenadasDTO posiciones;
    private String mensaje;

    public CoordenadasDTO getCoordenadasDTO() {
        return posiciones;
    }

    public void setCoordenadasDTO(CoordenadasDTO posiciones) {
        this.posiciones = posiciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
