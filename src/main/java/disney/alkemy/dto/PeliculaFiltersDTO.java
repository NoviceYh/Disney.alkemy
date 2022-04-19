package disney.alkemy.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PeliculaFiltersDTO {

    private String titulo;
    private Set<Long> genero;
    private String order;

    public PeliculaFiltersDTO(String titulo, Set<Long> genero, String order) {
        this.titulo = titulo;
        this.genero = genero;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }

}
