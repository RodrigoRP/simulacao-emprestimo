package br.com.rodrigoramos.desafiodbc.service.interfaces;

import br.com.rodrigoramos.desafiodbc.dto.EmprestimoNewDTO;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;

import java.util.List;

public interface EmprestimoService {

    Emprestimo convertDtoToModel(EmprestimoNewDTO emprestimoNewDTO);

    Emprestimo insert(Emprestimo emprestimo);

    Emprestimo find(Long id);

    List<Emprestimo> findAll();
}
