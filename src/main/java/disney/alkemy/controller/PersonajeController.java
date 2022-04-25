package disney.alkemy.controller;

import disney.alkemy.dto.PersonajeBasicDTO;
import disney.alkemy.dto.PersonajeDTO;
import disney.alkemy.entity.PersonajeEntity;
import disney.alkemy.service.PersonajeService;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getPersonajeById(@PathVariable Long id) {
        PersonajeDTO dto = personajeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<PersonajeBasicDTO>> getByFilters(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Set<Long> movies
    ) {
        List<PersonajeBasicDTO> dtos = personajeService.getByFilters(nombre, edad, movies);
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> savePersonaje(@Valid @RequestBody PersonajeDTO dto) {
        PersonajeDTO result = personajeService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> putPersonajeById(@PathVariable Long id, @Valid @RequestBody PersonajeDTO dto) {
        PersonajeDTO result = personajeService.updateById(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonajeEntity> deletePersonajeById(@PathVariable Long id) {
        personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
