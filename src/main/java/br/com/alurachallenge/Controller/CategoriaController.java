package br.com.alurachallenge.Controller;

import br.com.alurachallenge.Controller.dto.CategoriasDto;
import br.com.alurachallenge.Controller.dto.VideosDto;
import br.com.alurachallenge.Controller.form.AtualizacaoCategoriasForm;
import br.com.alurachallenge.Controller.form.AtualizacaoVideosForm;
import br.com.alurachallenge.Controller.form.CategoriasForm;
import br.com.alurachallenge.Controller.form.VideosForm;
import br.com.alurachallenge.model.Categorias;
import br.com.alurachallenge.model.Videos;
import br.com.alurachallenge.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @GetMapping
    public ResponseEntity<List<CategoriasDto>> getCategorias() {

        try {
            List<Categorias> categorias = categoriasRepository.findAll();
            return new ResponseEntity<>(CategoriasDto.converter(categorias), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDto> getCategoriasComId(@PathVariable long id) {
        Optional<Categorias> categorias = categoriasRepository.findById(id);

        if (categorias.isPresent()) {
            return new ResponseEntity<>(new CategoriasDto(categorias.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CategoriasDto> postCategorias (@RequestBody @Valid CategoriasForm form, UriComponentsBuilder uriBuilder) {
        Categorias categorias = form.converter(categoriasRepository);
        categoriasRepository.save(categorias);

        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(categorias.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriasDto(categorias));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoriasDto> updateCategorias(@PathVariable long id,
                                                  @RequestBody @Valid AtualizacaoCategoriasForm form) {
        Optional<Categorias> categoriasDados = categoriasRepository.findById(id);

        if (categoriasDados.isPresent()) {
            Categorias categorias = form.atualizar(id, categoriasRepository);
            return new ResponseEntity<>(new CategoriasDto(categorias), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
