package br.com.gspadaro.ordermanagerapi.controller;

import br.com.gspadaro.ordermanagerapi.dto.category.CategoryRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.category.CategoryResponseDTO;
import br.com.gspadaro.ordermanagerapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> create( @Valid @RequestBody CategoryRequestDTO request) {
        var category = service.create(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(category.id()).toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO category) {
        return ResponseEntity.ok().body(service.update(id, category));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
