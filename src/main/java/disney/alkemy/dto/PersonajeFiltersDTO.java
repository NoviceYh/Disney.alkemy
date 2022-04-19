package disney.alkemy.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonajeFiltersDTO {

    private String nombre;
    private Integer edad;
    private Set<Long> movies;

    public PersonajeFiltersDTO(String nombre, Integer edad, Set<Long> movies) {
        this.nombre = nombre;
        this.edad = edad;
        this.movies = movies;
    }
}
