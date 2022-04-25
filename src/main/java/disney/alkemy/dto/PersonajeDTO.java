package disney.alkemy.dto;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeDTO {

    private Long id;

    private String imagen;
    
    @NotNull
    private String nombre;
    
    @NotBlank
    private Integer edad;

    private Long peso;
    
    private String historia;

    private Set<PeliculaDTO> peliculas;
}
