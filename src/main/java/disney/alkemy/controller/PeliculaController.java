package disney.alkemy.controller;

import disney.alkemy.dto.PeliculaBasicDTO;
import disney.alkemy.dto.PeliculaDTO;
import disney.alkemy.service.PeliculaService;
import java.util.List;
import java.util.Set;
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
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getPeliculaById(@PathVariable Long id) {
        PeliculaDTO result = peliculaService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaBasicDTO>> getByFilters(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Set<Long> genero,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<PeliculaBasicDTO> dtos = peliculaService.getByFilters(titulo, genero, order);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO dto) {
        PeliculaDTO result = peliculaService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<PeliculaDTO> postCharacterInMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        PeliculaDTO dto = peliculaService.saveCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> updateMovie(@RequestBody PeliculaDTO dto, @PathVariable Long id) {
        PeliculaDTO result = peliculaService.updateById(dto, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovieById(@PathVariable Long id) {
        peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> deleteCharacterInMovie(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        peliculaService.deleteCharacter(idMovie, idCharacter);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
