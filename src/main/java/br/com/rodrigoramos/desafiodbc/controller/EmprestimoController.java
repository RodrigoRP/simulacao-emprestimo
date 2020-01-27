package br.com.rodrigoramos.desafiodbc.controller;

import br.com.rodrigoramos.desafiodbc.dto.EmprestimoNewDTO;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;
import br.com.rodrigoramos.desafiodbc.service.interfaces.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @Autowired
    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping(value = "/emprestimos")
    public ResponseEntity<Void> create(@RequestBody EmprestimoNewDTO emprestimoNewDTO) {
        Emprestimo emprestimo = emprestimoService.convertDtoToModel(emprestimoNewDTO);
        emprestimo = emprestimoService.insert(emprestimo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/emprestimos/{id}")
    public ResponseEntity<Emprestimo> find(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.find(id);
        return ResponseEntity.ok().body(emprestimo);
    }

    @GetMapping(value = "/emprestimos")
    public ResponseEntity<List<Emprestimo>> findAll() {
        List<Emprestimo> list = emprestimoService.findAll();
        return ResponseEntity.ok().body(list);
    }

}
