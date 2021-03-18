package com.rebelion.artifacttest.servicios;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.rebelion.artifacttest.model.CoordenadasDTO;
import com.rebelion.artifacttest.model.NaveDTO;
import com.rebelion.artifacttest.model.Nivel3DTO;
import com.rebelion.artifacttest.model.PosicionDTO;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService implements ImensajeService {

    private static final Logger logger = LoggerFactory.getLogger(MensajeService.class);

    double posiciones [][] = new double [][] {{-500.00 , 200.00}, {100.00, -100.00}, {500.00, 100.00}};

    private List<NaveDTO> listaNave = null;

    /**
     * Metodo para obtener las coordenadas de los emisores
     * @param naveDTOS Listado correspondientes a las distancias recibidas por JSON
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    public double[] obtenerDistancias (List<NaveDTO> naveDTOS){
        logger.debug("MensajeService::obtenerDistancias()");
        double[] array = new double[naveDTOS.size()];
        for (int i = 0; i<naveDTOS.size(); i++){
            array[i] = naveDTOS.get(i).getDistance();
        }
        return array;
    }

    /**
     * Metodo para obtener las informacion de cada satelite (mensaje nombre y distancia)
     * @param nombre Listado correspondientes a las distancias recibidas por JSON
     * @param nivel3DTO Objeto correspondientes a lo recibido por JSON
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    public NaveDTO validarListaSatelites(String nombre, Nivel3DTO nivel3DTO){
        logger.debug("MensajeService::validarListaSatelites()");
        for(NaveDTO naveDTO : listaNave){
            if(naveDTO.getName().equalsIgnoreCase(nombre)){
                naveDTO.setMessage(nivel3DTO.getMessage());
                naveDTO.setDistance(nivel3DTO.getDistance());
                return naveDTO;
            }
        }
        return null;
    }

    /**
     * Metodo que calcula las coordenadas de los satelites
     * @param naveDTOS informacion que llega desde la nave (ubicacion, distancia)
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    @Override
    public CoordenadasDTO asignarCoordenadasNave(List<NaveDTO> naveDTOS){
        logger.debug("MensajeService::asignarCoordenadasNave()");
        CoordenadasDTO posiciones = new CoordenadasDTO();
        double posicion [][] = new double [][] {{-500.00 , 200.00}, {100.00, -100.00}, {500.00, 100.00}};
        double  distances[] = obtenerDistancias(naveDTOS);
        try {
            NonLinearLeastSquaresSolver trilateracion = new NonLinearLeastSquaresSolver(new TrilaterationFunction(posicion, distances), new LevenbergMarquardtOptimizer());
            LeastSquaresOptimizer.Optimum optimum = trilateracion.solve();
            double centroid[] = optimum.getPoint().toArray();
            posiciones.setY(centroid[1]);
            posiciones.setX(centroid[0]);

        } catch (Exception e){
        }
        return posiciones;
    }

    /**
     * Metodo que asigna las coordenadas del satelite.
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    public CoordenadasDTO asignarCoordenadas(){
        logger.debug("MensajeService::asignarCoordenadas()");
        CoordenadasDTO coordenadas = new CoordenadasDTO();
        try {
            if(listaNave != null && !listaNave.isEmpty()){
                double posicion [][] = new double [listaNave.size()][];
                double distances[] = obtenerDistancias(listaNave);
                for(int i = 0; i<listaNave.size(); i++){
                    switch (listaNave.get(i).getName()){
                        case "kenobi":
                            posicion[i] = posiciones[0];
                            break;

                        case "skywalker":
                            posicion[i] = posiciones[1];
                            break;

                        case "solo":
                            posicion[i] = posiciones[2];
                            break;
                    }
                }
                NonLinearLeastSquaresSolver trilateracion = new NonLinearLeastSquaresSolver(new TrilaterationFunction(posicion, distances), new LevenbergMarquardtOptimizer());
                LeastSquaresOptimizer.Optimum optimum = trilateracion.solve();
                double centroid[] = optimum.getPoint().toArray();
                coordenadas.setY(centroid[1]);
                coordenadas.setX(centroid[0]);
            }
        } catch (Exception e){

        }
        return coordenadas;
    }


    /**
     * Metodo que obtiene los mensajes de cada satelite.
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    public List<String[]> obtenerMensajes(List<NaveDTO> naveDTOS){
        logger.debug("MensajeService::obtenerMensajes()");
        List<String[]> mensajes = new ArrayList<>();
        for (int i=0; i<naveDTOS.size(); i++){
            mensajes.add(naveDTOS.get(i).getMessage());
        }
        return mensajes;
    }


    /**
     * Metodo que organiza el mensaje para el staelite kenobi
     * @param mensajes informacion que llega desde la nave (mensaje)
     * @author Camilo.Albor
     * @since 17/03/2021
*/
    public String organizarMensaje(List<String[]> mensajes){
        logger.debug("MensajeService::organizarMensaje()");
        String mensajeRespuesta = "";
        String[] mensajeke = mensajes.get(0);
        String[] mensajesk = mensajes.get(1);
        String[] mensajeso = mensajes.get(2);
        String[] mensajesArray = new String[5];
        try {
        for(int i = 0; i<mensajesArray.length; i++){
             if(mensajeke[i] == ""){
                 if(mensajesk[i] == ""){
                     mensajesArray[i] = mensajeso[i]+" ";
                 }else{
                     mensajesArray[i] = mensajesk[i]+" ";
                 }
             }else{
                 mensajesArray[i] = mensajeke[i]+" ";
             }
        }
        for (int y = 0; y<mensajesArray.length; y++){
            mensajeRespuesta = mensajeRespuesta.concat(String.valueOf(mensajesArray[y]));
        }

    } catch (Exception ee){
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Error.", ee);
    }
        return mensajeRespuesta;
    }

    /**
     * Metodo que guard al informacion en la variable local del listado de info de satelites
     * @param nombreSatelite nombre del satelite, nos sirve para vvalidar si ya existe solo actualizar la entidad sino la crea
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    public NaveDTO guardarInfoSatelite(Nivel3DTO nivel3DTO, String nombreSatelite){
        logger.debug("MensajeService::guardarInfoSatelite()");
        if(listaNave == null){
            listaNave = new ArrayList<>();
        }
        NaveDTO nave = validarListaSatelites(nombreSatelite,nivel3DTO);
        if( nave == null ){
            nave = new NaveDTO();
            nave.setName(nombreSatelite);
            nave.setDistance(nivel3DTO.getDistance());
            nave.setMessage(nivel3DTO.getMessage());
            listaNave.add(nave);
        }
        return nave;
    }

    /**
     * Metodo que consulta la informacion guardada en la variable local.
     * @author Camilo.Albor
     * @since 17/03/2021
     */
    public PosicionDTO consultarInformacionSatelite(){
        logger.debug("MensajeService::PosicionDTO()");
        PosicionDTO posicionDTO = new PosicionDTO();
        CoordenadasDTO posiciones = asignarCoordenadas();
        if(posiciones != null){
            posicionDTO.setCoordenadasDTO(posiciones);
            List<String[]> mensajeSatelites = obtenerMensajes(listaNave);
            String mensaje = organizarMensaje(mensajeSatelites);
            posicionDTO.setMensaje(mensaje);
            return posicionDTO;
        }
        return posicionDTO;
    }
}
