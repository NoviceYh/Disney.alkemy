package disney.alkemy.dto;

import java.time.LocalDate;
import java.util.Set;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;

    private String imagen;
    
    @NotNull
    private String titulo;
    
    private LocalDate fechaCreacion;

    @Max(5) 
    @Min(1)
    private Integer calificacion;

    private GeneroDTO genero;

    private Set<PersonajeDTO> personajes;
}
