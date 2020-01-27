package br.com.rodrigoramos.desafiodbc.controller;

import br.com.rodrigoramos.desafiodbc.dto.ClienteNewDTO;
import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping(value = "/clientes")
    public ResponseEntity<Void> create(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
        Cliente cliente = clienteService.convertDtoToModel(clienteNewDTO);
        cliente = clienteService.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/clientes/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Long id) {
        Cliente cliente = clienteService.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping(value = "/clientes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/clientes")
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value="/clientes/{id}")
    public ResponseEntity<Void> update(@RequestBody ClienteNewDTO clienteDTO, @PathVariable Long id) {
        Cliente cliente = clienteService.convertDtoToModel(clienteDTO);
        cliente.setId(id);
        cliente = clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }
}
