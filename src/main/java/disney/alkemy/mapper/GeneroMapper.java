package disney.alkemy.mapper;

import disney.alkemy.dto.GeneroDTO;
import disney.alkemy.entity.GeneroEntity;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {

    //Dto to Entity Save
    public GeneroEntity generoDTO2Entity(GeneroDTO dto) {
        GeneroEntity entity = new GeneroEntity();
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        return entity;
    }

    //Entity to DTO Get
    public GeneroDTO generoEntity2DTO(GeneroEntity entity) {
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setImagen(entity.getImagen());
        dto.setNombre(entity.getNombre());
        return dto;
    }
}
