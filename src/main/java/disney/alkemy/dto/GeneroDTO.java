package disney.alkemy.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneroDTO {

    private Long id;
    
    @NotNull
    private String nombre;

    private String imagen;

}
