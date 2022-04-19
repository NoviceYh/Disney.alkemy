package disney.alkemy.service;

import disney.alkemy.dto.PersonajeBasicDTO;
import disney.alkemy.dto.PersonajeDTO;
import java.util.List;
import java.util.Set;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);

    PersonajeDTO findById(Long id);

    PersonajeDTO updateById(PersonajeDTO dto, Long id);

    List<PersonajeBasicDTO> getByFilters(String nombre, Integer edad, Set<Long> movies);

    public void delete(Long id);

}
