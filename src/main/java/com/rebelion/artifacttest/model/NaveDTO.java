package com.rebelion.artifacttest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO para representar la informacion que llega al servicio ppal
 * @author Camilo.Albor
 * @since 17/03/2021
 */
public class NaveDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Double distance;
    private String[] message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String[] getMessage() {
        return message;
    }

    public void setMessage(String[] message) {
        this.message = message;
    }
}
