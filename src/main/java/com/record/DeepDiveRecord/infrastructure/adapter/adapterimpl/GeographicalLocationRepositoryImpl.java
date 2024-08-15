package com.record.DeepDiveRecord.infrastructure.adapter.adapterimpl;
import com.record.DeepDiveRecord.domain.model.dto.port.geographical_location.FindGeographicalLocation;
import com.record.DeepDiveRecord.domain.model.exception.EntityNotFoundException;
import com.record.DeepDiveRecord.domain.port.GeographicalLocationPort;
import com.record.DeepDiveRecord.infrastructure.adapter.entity.GeographicalLocationEntity;
import com.record.DeepDiveRecord.infrastructure.adapter.repository.geographical_location.GeographicalLocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GeographicalLocationRepositoryImpl implements GeographicalLocationPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeographicalLocationRepositoryImpl.class);
    @Autowired
    private GeographicalLocationRepository geographicalLocationRepository;

    /**
     * Método para buscar una entidad de GeographicalLocation por nombre y sitio.
     * @param findGeographicalLocation DTO con el nombre y el sitio a buscar.
     * @return GeographicalLocationEntity si se encuentra una coincidencia.
     * @throws EntityNotFoundException si no se encuentra una coincidencia.
     */
    @Override
    public GeographicalLocationEntity findByNameAndSite(FindGeographicalLocation findGeographicalLocation) {
        LOGGER.info("Comienza el metodo findByNameAndSite con los datos {} y {} ", findGeographicalLocation.getName(), findGeographicalLocation.getSite());

        // Obtención de todas las entidades de GeographicalLocation.
        List<GeographicalLocationEntity> list =  geographicalLocationRepository.findAll();

        // Iteración sobre la lista para encontrar la entidad que coincida con el nombre y el sitio.
        for(GeographicalLocationEntity item : list){
            if(item.getName().equals(findGeographicalLocation.getName()) && item.getSite().equals(findGeographicalLocation.getSite())){
                // Registro de éxito y retorno de la entidad encontrada.
                LOGGER.info("Se retorna con id {} ", item.getId());
                return item;
            }
        }
        // Registro de error si no se encuentra la entidad y lanzamiento de excepción.

        LOGGER.info("Error al encontra la GeographicalLocationEntity");
        throw new EntityNotFoundException("Geographical location not found");

    }
    /**
     * Método para obtener todas las entidades de GeographicalLocation.
     * @return Lista de GeographicalLocationEntity.
     * @throws IllegalArgumentException si la lista está vacía.
     */
    @Override
    public List<GeographicalLocationEntity> getAllGeGeographicalLocation() {
        LOGGER.info("Comienza el metodo getAllGeGeographicalLocation");

        // Obtención de todas las entidades de GeographicalLocation.
        List<GeographicalLocationEntity> geograficEntityList = geographicalLocationRepository.findAll();
        // Verificación si la lista está vacía y lanzamiento de excepción si es así.
        if (geograficEntityList.isEmpty()) {
            throw new IllegalArgumentException("Geographical location not found");
        }
        // Retorno de la lista de entidades.
        return geograficEntityList;
    }
    /**
     * Método para buscar una entidad de GeographicalLocation por ID.
     * @param idGeograficLocation ID de la ubicación geográfica a buscar.
     * @return GeographicalLocationEntity si se encuentra.
     * @throws EntityNotFoundException si no se encuentra.
     */
    @Override
    public GeographicalLocationEntity findById(Integer idGeograficLocation) {
        LOGGER.info("Se porcede a buscar GeographicalLocationEntity por el id {}", idGeograficLocation);
        // Búsqueda de la entidad por ID utilizando el repositorio.
        Optional<GeographicalLocationEntity> location = geographicalLocationRepository.findById(idGeograficLocation);

        // Verificación si la entidad está presente y retorno si es así.
        if(location.isPresent()){
            LOGGER.info("encontrada");
            return location.get();
        }
        // Registro de error si no se encuentra la entidad y lanzamiento de excepción.

        LOGGER.info("No se encontro");
        throw new EntityNotFoundException("Geographical location not found");
    }
}