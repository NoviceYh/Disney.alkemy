package disney.alkemy.mapper;

import disney.alkemy.dto.GeneroDTO;
import disney.alkemy.dto.PeliculaBasicDTO;
import disney.alkemy.dto.PeliculaDTO;
import disney.alkemy.dto.PersonajeDTO;
import disney.alkemy.entity.PeliculaEntity;
import disney.alkemy.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeliculaMapper {

    @Autowired
    private GeneroMapper generoMapper;

    @Autowired
    private PersonajeMapper personajeMapper;

    //DTO to Entity -- OK
    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto) {
        PeliculaEntity entity = new PeliculaEntity();
        entity.setCalificacion(dto.getCalificacion());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        //Genero    
//        GeneroEntity generoEntity = generoRepository.findById(dto.getGenero().getId())
//                .orElseThrow(() -> new ParamNotFound("El id de genero no es valido."));
//        entity.setGenero(generoEntity);
        //Personaje
        Set<PersonajeEntity> personajes = personajeMapper.listPersonajeDTO2Entity(dto.getPersonajes());
        entity.setPersonajes(personajes);

        return entity;
    }

    //Update Pelicula -- OK
    public PeliculaEntity updatePeliculaDTO2Entity(PeliculaEntity entity, PeliculaDTO dto) {
        entity.setCalificacion(dto.getCalificacion());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
//        GeneroEntity generoEntity = generoRepository.findById(dto.getGenero().getId())
//                .orElseThrow(() -> new ParamNotFound("El id de genero no es valido"));
//        entity.setGenero(generoEntity);
        return entity;
    }

    //Entidad a DTO -- OK
    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean loadGeneros, boolean loadPeliculas) {
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setImagen(entity.getImagen());
        dto.setCalificacion(entity.getCalificacion());
        dto.setFechaCreacion(entity.getFechaCreacion());
        //Genero
        if (loadGeneros) {
            GeneroDTO generoDto = generoMapper.generoEntity2DTO(entity.getGenero());
            dto.setGenero(generoDto);
        }
        //Personajes
        if (loadPeliculas) {
            Set<PersonajeDTO> personajes = personajeMapper.listPersonajeEntity2DTO(entity.getPersonajes(), false);
            dto.setPersonajes(personajes);
        }
        return dto;
    }

    //Metodo privado de 'listPeliculaBasicDTO' -- OK
    private PeliculaBasicDTO peliculaEntity2BasicDTO(PeliculaEntity entity) {
        PeliculaBasicDTO dto = new PeliculaBasicDTO();
        dto.setId(entity.getId());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        return dto;
    }

    //List Entity to BasicDTO -- OK
    public List<PeliculaBasicDTO> listEntity2PeliculaBasicDTO(List<PeliculaEntity> entities) {
        List<PeliculaBasicDTO> dtos = new ArrayList<>();
        PeliculaBasicDTO dto;
        for (PeliculaEntity entity : entities) {
            dto = peliculaEntity2BasicDTO(entity);
            dtos.add(dto);
        }
        return dtos;
    }

    //Set Entity to DTO -- OK
    public Set<PeliculaDTO> listEntity2DTO(Set<PeliculaEntity> entities, boolean loadPersonajes) {
        Set<PeliculaDTO> dtos = new HashSet<>();
        PeliculaDTO dto;
        if (entities == null) {
            dtos = null;
        } else {
            for (PeliculaEntity entity : entities) {
                dto = peliculaEntity2DTO(entity, true, loadPersonajes);
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
