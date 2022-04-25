package disney.alkemy.service.impl;

import disney.alkemy.dto.PeliculaBasicDTO;
import disney.alkemy.dto.PeliculaDTO;
import disney.alkemy.dto.PeliculaFiltersDTO;
import disney.alkemy.entity.GeneroEntity;
import disney.alkemy.entity.PeliculaEntity;
import disney.alkemy.entity.PersonajeEntity;
import disney.alkemy.exception.ErrorEnum;
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
        GeneroEntity genero = generoRepository.findById(dto.getGenero().getId())
                .orElseThrow(() -> new ParamNotFound(ErrorEnum.IDMOVIENOTVALID.getMessage()));
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        entity.setGenero(genero);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved, true, true);
        return result;
    }

    @Override // -- OK
    public PeliculaDTO getById(Long id) {
        PeliculaEntity entity = peliculaRepository.findById(id)
                .orElseThrow(() -> new ParamNotFound(ErrorEnum.IDMOVIENOTVALID.getMessage()));
        PeliculaDTO dto = peliculaMapper.peliculaEntity2DTO(entity, true, true);
        return dto;
    }

    @Override // -- OK
    public PeliculaDTO updateById(PeliculaDTO dto, Long id) {
        GeneroEntity genero = generoRepository.findById(dto.getGenero().getId())
                .orElseThrow(() -> new ParamNotFound(ErrorEnum.IDGENRENOTVALID.getMessage()));
        PeliculaEntity entity = peliculaRepository
                .findById(id).orElseThrow(() -> new ParamNotFound(ErrorEnum.IDMOVIENOTVALID.getMessage()));
        entity = peliculaMapper.updatePeliculaDTO2Entity(entity, dto);
        entity.setGenero(genero);
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
                .findById(idMovie).orElseThrow(() -> new ParamNotFound(ErrorEnum.IDMOVIENOTVALID.getMessage()));
        PersonajeEntity character = personajeRepository.findById(idCharacter)
                .orElseThrow(() -> new ParamNotFound(ErrorEnum.IDCHARACTERNOTVALID.getMessage()));
        //Update
        Set<PersonajeEntity> personajes = movie.getPersonajes();
        personajes.add(character);
        //movie.setPersonajes(personajes); No hace falta
        peliculaRepository.save(movie);
        //
        PeliculaDTO dto = peliculaMapper.peliculaEntity2DTO(movie, true, true);
        return dto;
    }

    @Override // -- OK
    public void delete(Long id) {
        PeliculaEntity entity = peliculaRepository.findById(id)
                .orElseThrow(() -> new ParamNotFound(ErrorEnum.IDMOVIENOTVALID.getMessage()));
        peliculaRepository.deleteById(id);
    }

    @Override // -- OK
    public void deleteCharacter(Long idMovie, Long idCharacter) {
        PeliculaEntity movie = peliculaRepository
                .findById(idMovie).orElseThrow(() -> new ParamNotFound(ErrorEnum.IDMOVIENOTVALID.getMessage()));
        PersonajeEntity character = personajeRepository.findById(idCharacter)
                .orElseThrow(() -> new ParamNotFound(ErrorEnum.IDCHARACTERNOTVALID.getMessage()));
        //DeleteCharacter
        //Luego cambiar por el uso del metodo equals para eliminar
        Set<PersonajeEntity> personajes = movie.getPersonajes();
        personajes.remove(character);
        //movie.setPersonajes(personajes); No hace falta
        peliculaRepository.save(movie);
    }
}
