package com.rebelion.artifacttest.servicios;

import com.rebelion.artifacttest.model.CoordenadasDTO;
import com.rebelion.artifacttest.model.NaveDTO;
import com.rebelion.artifacttest.model.Nivel3DTO;
import com.rebelion.artifacttest.model.PosicionDTO;

import java.util.List;

public interface ImensajeService {

    public CoordenadasDTO asignarCoordenadasNave(List<NaveDTO> naveDTOS);
    public List<String[]> obtenerMensajes(List<NaveDTO> naveDTOS);
    public String organizarMensaje(List<String[]> mensajes);
    public NaveDTO guardarInfoSatelite(Nivel3DTO nivel3DTO, String mensajeSatelite);
    public CoordenadasDTO asignarCoordenadas();
    public PosicionDTO consultarInformacionSatelite();
}
