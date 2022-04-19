package disney.alkemy.service.impl;

import disney.alkemy.dto.PersonajeBasicDTO;
import disney.alkemy.dto.PersonajeDTO;
import disney.alkemy.dto.PersonajeFiltersDTO;
import disney.alkemy.entity.PersonajeEntity;
import disney.alkemy.exception.ParamNotFound;
import disney.alkemy.mapper.PersonajeMapper;
import disney.alkemy.repository.PersonajeRepository;
import disney.alkemy.repository.specification.PersonajeSpecification;
import disney.alkemy.service.PersonajeService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PersonajeMapper personajeMapper;

    @Autowired
    private PersonajeSpecification personajeSpecification;

    @Override // -- OK
    public PersonajeDTO save(PersonajeDTO dto) {
        PersonajeEntity entity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved, true);
        return result;
    }

    @Override // -- OK
    public PersonajeDTO findById(Long id) {
        PersonajeEntity entity = personajeRepository.findById(id)
                .orElseThrow(() -> new ParamNotFound("El id del personaje no es valido."));        
        PersonajeDTO dto = personajeMapper.personajeEntity2DTO(entity, true);
        return dto;
    }

    //revisar
    @Override // -- OK
    public PersonajeDTO updateById(PersonajeDTO dto, Long id) {
        PersonajeEntity entity = personajeRepository.findById(id)
                .orElseThrow(() -> new ParamNotFound("El id del personaje no es valido."));        
        entity.setEdad(dto.getEdad());
        entity.setHistoria(dto.getHistoria());
        entity.setImagen(dto.getImagen());
        entity.setNombre(dto.getNombre());
        entity.setPeso(dto.getPeso());
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personajeRepository.save(entity), true);
        return result;
    }

    @Override // -- OK
    public List<PersonajeBasicDTO> getByFilters(String nombre, Integer edad, Set<Long> movies) {
        PersonajeFiltersDTO filterDTO = new PersonajeFiltersDTO(nombre, edad, movies);
        List<PersonajeEntity> entities = personajeRepository.findAll(personajeSpecification.getByFilters(filterDTO));
        List<PersonajeBasicDTO> dtos = personajeMapper.listPersonajeEntity2BasicDTO(entities);
        return dtos;
    }

    @Override // -- OK
    public void delete(Long id) {        
        PersonajeEntity entity = personajeRepository.findById(id)
                .orElseThrow(() -> new ParamNotFound("El id del personaje no es valido"));
        personajeRepository.deleteById(id);
    }

}
