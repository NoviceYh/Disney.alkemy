package disney.alkemy.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "personaje")
public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Long peso;

    private String historia;
    
    //Un Personaje no puede crear una pelicula, pero si de una pelicula se puede crear un personaje
    @ManyToMany(mappedBy = "personajes",
            fetch = FetchType.LAZY)
    private Set<PeliculaEntity> peliculas;

}
