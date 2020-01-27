package br.com.rodrigoramos.desafiodbc.service.interfaces;

import br.com.rodrigoramos.desafiodbc.dto.ClienteNewDTO;
import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;

import java.util.List;

public interface ClienteService {
    Cliente convertDtoToModel(ClienteNewDTO clienteNewDTO);

    Cliente insert(Cliente cliente);

    Cliente find(Long id);

    void delete(Long id);

    List<Cliente> findAll();

    Cliente update(Cliente cliente);

    Emprestimo simularEmprestimo(Cliente cliente, Double valorRequerido, int nroParcelas);

    }
