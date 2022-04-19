package disney.alkemy.service;

import disney.alkemy.dto.PeliculaBasicDTO;
import disney.alkemy.dto.PeliculaDTO;
import java.util.List;
import java.util.Set;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO dto);

    PeliculaDTO getById(Long id);

    PeliculaDTO updateById(PeliculaDTO dto, Long id);

    public List<PeliculaBasicDTO> getByFilters(String titulo, Set<Long> genero, String order);

    public PeliculaDTO saveCharacter(Long idMovie, Long idCharacter);

    public void delete(Long id);

    public void deleteCharacter(Long idMovie, Long idCharacter);
}
