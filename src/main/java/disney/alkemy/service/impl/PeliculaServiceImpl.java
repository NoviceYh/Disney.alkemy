package disney.alkemy.service.impl;

import disney.alkemy.dto.PeliculaBasicDTO;
import disney.alkemy.dto.PeliculaDTO;
import disney.alkemy.dto.PeliculaFiltersDTO;
import disney.alkemy.entity.GeneroEntity;
import disney.alkemy.entity.PeliculaEntity;
import disney.alkemy.entity.PersonajeEntity;
import disney.alkemy.exception.ParamNotFound;
import disney.alkemy.mapper.PeliculaMapper;
import disney.alkemy.repository.GeneroRepository;
import disney.alkemy.repository.PeliculaRepository;
import disney.alkemy.repository.PersonajeRepository;
import disney.alkemy.repository.specification.PeliculaSpecification;
import disney.alkemy.service.PeliculaService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private PeliculaMapper peliculaMapper;

    @Autowired
    private PeliculaSpecification peliculaSpecification;

    @Autowired
    private GeneroRepository generoRepository;

   

    @Override // -- OK
    public PeliculaDTO save(PeliculaDTO dto) {
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved, true, true);
        return result;
    }

    @Override // -- OK
    public PeliculaDTO getById(Long id) {
        PeliculaEntity entity = peliculaRepository.findById(id)
                .orElseThrow(() -> new ParamNotFound("El id de la Pelicula no existe."));
        PeliculaDTO dto = peliculaMapper.peliculaEntity2DTO(entity, true, true);
        return dto;
    }

    @Override // -- OK
    public PeliculaDTO updateById(PeliculaDTO dto, Long id) {
        PeliculaEntity entity = peliculaRepository
                .findById(id).orElseThrow(() -> new ParamNotFound("El id de la pelicula no es valido."));
        entity = peliculaMapper.updatePeliculaDTO2Entity(entity, dto);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(peliculaRepository.save(entity), true, true);
        return result;
    }

    @Override // -- OK
    public List<PeliculaBasicDTO> getByFilters(String titulo, Set<Long> genero, String order) {
        PeliculaFiltersDTO filterDTO = new PeliculaFiltersDTO(titulo, genero, order);
        List<PeliculaEntity> entities = peliculaRepository.findAll(peliculaSpecification.getByFilters(filterDTO));
        List<PeliculaBasicDTO> dtos = peliculaMapper.listEntity2PeliculaBasicDTO(entities);
        return dtos;
    }

    @Override // -- OK
    public PeliculaDTO saveCharacter(Long idMovie, Long idCharacter) {
        PeliculaEntity movie = peliculaRepository
                .findById(idMovie).orElseThrow(() -> new ParamNotFound("El id de la pelicula no es valido."));
        PersonajeEntity character = personajeRepository.findById(idCharacter)
                .orElseThrow(() -> new ParamNotFound("El id del personaje no es valido."));
        //Update
        Set<PersonajeEntity> personajes = movie.getPersonajes();
        personajes.add(character);
        movie.setPersonajes(personajes);
        peliculaRepository.save(movie);
        //
        PeliculaDTO dto = peliculaMapper.peliculaEntity2DTO(movie, true, true);
        return dto;
    }

    @Override // -- OK
    public void delete(Long id) {
        PeliculaEntity entity = peliculaRepository.findById(id)
                .orElseThrow(() -> new ParamNotFound("El id de la pelicula no es valido."));
        peliculaRepository.deleteById(id);
    }

    @Override // -- OK
    public void deleteCharacter(Long idMovie, Long idCharacter) {
        PeliculaEntity movie = peliculaRepository
                .findById(idMovie).orElseThrow(() -> new ParamNotFound("El id de la pelicula no es valido."));
        PersonajeEntity character = personajeRepository.findById(idCharacter)
                .orElseThrow(() -> new ParamNotFound("El id del personaje no es valido."));
        //DeleteCharacter
        //Luego cambiar por el uso del metodo equals para eliminar
        Set<PersonajeEntity> personajes = movie.getPersonajes();
        personajes.remove(character);
        movie.setPersonajes(personajes);
        peliculaRepository.save(movie);
    }
}
