package disney.alkemy.controller;

import disney.alkemy.dto.GeneroDTO;
import disney.alkemy.service.GeneroService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genres")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@Valid @RequestBody GeneroDTO dto) {
        GeneroDTO result = generoService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /*
    Trae todos los generos
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> dtos = generoService.getAll();
        return ResponseEntity.ok().body(dtos);
    }
    
    Busca Genero por Id
    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> getById(@PathVariable Long id){
        GeneroDTO dto = generoService.getGeneroById(id);
        return ResponseEntity.ok(dto);
    }*/
}
