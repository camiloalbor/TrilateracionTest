package com.rebelion.artifacttest.model;

import java.io.Serializable;

/**
 * DTO para representar la informacion de las coordenadas (Respuesta servicio)
 * @author Camilo.Albor
 * @since 18/03/2021
 */
public class CoordenadasDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double x;
    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
