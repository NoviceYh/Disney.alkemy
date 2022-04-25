package disney.alkemy.mapper;

import disney.alkemy.dto.PeliculaDTO;
import disney.alkemy.dto.PersonajeBasicDTO;
import disney.alkemy.dto.PersonajeDTO;
import disney.alkemy.entity.PersonajeEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

    //DTO a Entidad Save -- OK
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto) {
        PersonajeEntity entity = new PersonajeEntity();
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setPeso(dto.getPeso());
        return entity;
    }
    
    //Update
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto, PersonajeEntity entity) {
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setPeso(dto.getPeso());
        return entity;
    }
    

    //Entity to DTO GET -- OK
    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity, boolean loadPeliculas) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        dto.setHistoria(entity.getHistoria());
        dto.setPeso(entity.getPeso());
        dto.setEdad(entity.getEdad());
        if (loadPeliculas) {
            Set<PeliculaDTO> peliculas = peliculaMapper.listEntity2DTO(entity.getPeliculas(), false);
            dto.setPeliculas(peliculas);
        }
        return dto;
    }

    //List Entity to BasicDTO -- OK
    public List<PersonajeBasicDTO> listPersonajeEntity2BasicDTO(List<PersonajeEntity> entities) {
        List<PersonajeBasicDTO> dtos = new ArrayList<>();
        PersonajeBasicDTO dto;
        for (PersonajeEntity entity : entities) {
            dto = new PersonajeBasicDTO();
            dto.setId(entity.getId());
            dto.setImagen(entity.getImagen());
            dto.setNombre(entity.getNombre());
            dtos.add(dto);
        }
        return dtos;
    }

    //List DTO to Entity (Mapper Pelicula) -- OK
    public Set<PersonajeEntity> listPersonajeDTO2Entity(Set<PersonajeDTO> dtos) {
        Set<PersonajeEntity> entities = new HashSet<>();
        PersonajeEntity entity;
        for (PersonajeDTO dto : dtos) {
            entity = personajeDTO2Entity(dto);
            entities.add(entity);
        }
        return entities;
    }

    //Set Entidad to DTO -- OK
    public Set<PersonajeDTO> listPersonajeEntity2DTO(Set<PersonajeEntity> entities, boolean loadPeliculas) {
        Set<PersonajeDTO> dtos = new HashSet<>();
        PersonajeDTO dto;
        for (PersonajeEntity entity : entities) {
            dto = personajeEntity2DTO(entity, loadPeliculas);
            dtos.add(dto);
        }
        return dtos;
    }

}
