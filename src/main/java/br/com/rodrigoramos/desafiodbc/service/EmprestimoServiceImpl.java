package br.com.rodrigoramos.desafiodbc.service;

import br.com.rodrigoramos.desafiodbc.dto.EmprestimoNewDTO;
import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;
import br.com.rodrigoramos.desafiodbc.repository.EmprestimoRepository;
import br.com.rodrigoramos.desafiodbc.service.exception.ObjectNotFoundException;
import br.com.rodrigoramos.desafiodbc.service.interfaces.ClienteService;
import br.com.rodrigoramos.desafiodbc.service.interfaces.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private ClienteService clienteService;


    @Autowired
    public EmprestimoServiceImpl(EmprestimoRepository emprestimoRepository, ClienteService clienteService) {
        this.emprestimoRepository = emprestimoRepository;
        this.clienteService = clienteService;
    }

    @Override
    public Emprestimo convertDtoToModel(EmprestimoNewDTO emprestimoNewDTO) {
        Cliente cliente = clienteService.find(emprestimoNewDTO.getIdCliente());
        return clienteService.simularEmprestimo(cliente,
                emprestimoNewDTO.getValorRequerido(),
                emprestimoNewDTO.getNroParcelas());
    }

    @Override
    public Emprestimo insert(Emprestimo emprestimo) {
        emprestimo = emprestimoRepository.save(emprestimo);
        return emprestimo;
    }

    @Override
    public Emprestimo find(Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findByIdCliente(id);
        return emprestimo.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Override
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }
}

