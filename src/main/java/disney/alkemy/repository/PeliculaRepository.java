package disney.alkemy.repository;

import disney.alkemy.entity.PeliculaEntity;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

    List<PeliculaEntity> findAll(Specification<PeliculaEntity> spec);

}
