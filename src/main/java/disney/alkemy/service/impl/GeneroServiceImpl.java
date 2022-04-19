package disney.alkemy.service.impl;

import disney.alkemy.dto.GeneroDTO;
import disney.alkemy.entity.GeneroEntity;
import disney.alkemy.mapper.GeneroMapper;
import disney.alkemy.repository.GeneroRepository;
import disney.alkemy.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private GeneroMapper mapper;

    @Override
    public GeneroDTO save(GeneroDTO dto) {
        GeneroEntity entity = mapper.generoDTO2Entity(dto);
        GeneroEntity entitySaved = generoRepository.save(entity);
        GeneroDTO result = mapper.generoEntity2DTO(entitySaved);
        return result;
    }
}
