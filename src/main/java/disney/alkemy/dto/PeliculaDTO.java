package disney.alkemy.dto;

import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String imagen;

    private String titulo;

    private LocalDate fechaCreacion;

    private Integer calificacion;

    private GeneroDTO genero;

    private Set<PersonajeDTO> personajes;
}
