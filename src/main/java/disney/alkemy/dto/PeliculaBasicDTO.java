package disney.alkemy.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaBasicDTO {

    private Long id;

    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;

}
