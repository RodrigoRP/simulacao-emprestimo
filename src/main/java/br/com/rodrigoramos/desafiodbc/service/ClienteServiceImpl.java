package br.com.rodrigoramos.desafiodbc.service;

import br.com.rodrigoramos.desafiodbc.dto.ClienteNewDTO;
import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;
import br.com.rodrigoramos.desafiodbc.model.Endereco;
import br.com.rodrigoramos.desafiodbc.model.enums.TipoCliente;
import br.com.rodrigoramos.desafiodbc.repository.ClienteRepository;
import br.com.rodrigoramos.desafiodbc.repository.EnderecoRepository;
import br.com.rodrigoramos.desafiodbc.service.exception.ObjectNotFoundException;
import br.com.rodrigoramos.desafiodbc.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Cliente convertDtoToModel(ClienteNewDTO clienteDTO) {
        Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getSobrenome(),
                clienteDTO.getEmail(), clienteDTO.getCpf(), clienteDTO.getRendimentoMensal(),
                null);
        Endereco endereco = new Endereco(null, clienteDTO.getNumero(), clienteDTO.getRua(),
                clienteDTO.getBairro(), clienteDTO.getCep(), cliente);
        cliente.getEnderecos().add(endereco);
        return cliente;
    }

    @Override
    public Cliente insert(Cliente cliente) {
        cliente = calcularRisco(cliente);
        cliente = clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
    }

    @Override
    public Cliente find(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Override
    public void delete(Long id) {
        find(id);
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente update(Cliente cliente) {
        Cliente newCliente = find(cliente.getId());
        updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }

    private void updateData(Cliente newCliente, Cliente cliente) {
        if (cliente.getRendimentoMensal() != null) {
            newCliente.setRendimentoMensal(cliente.getRendimentoMensal());
        }
    }

    public Cliente calcularRisco(Cliente cliente) {
        if (cliente.getRendimentoMensal() <= 2000.00) {
            cliente.setTipo(TipoCliente.TIPO_C);
        } else if (cliente.getRendimentoMensal() > 2000.00 && cliente.getRendimentoMensal() <= 8000.00) {
            cliente.setTipo(TipoCliente.TIPO_B);
        } else if (cliente.getRendimentoMensal() > 8000.00) {
            cliente.setTipo(TipoCliente.TIPO_A);
        }
        return cliente;
    }

    @Override
    public Emprestimo simularEmprestimo(Cliente cliente, Double valorRequerido, int nroParcelas) {
        Double taxaJuros = getJuros(cliente);
        Double juros = valorRequerido * taxaJuros * nroParcelas;
        Double valorTotal = juros + valorRequerido;
        Double valorParcela = valorTotal / nroParcelas;

        Emprestimo emprestimo = new Emprestimo(null, nroParcelas, valorRequerido,
                valorTotal, valorParcela, cliente.getId());
        return emprestimo;
    }

    public Double getJuros(Cliente cliente) {
        Double taxaJuros = 0.0;
        if (cliente.getTipo().getCod() == 1) {
            taxaJuros = 1.9 / 100;
        } else if (cliente.getTipo().getCod() == 2) {
            taxaJuros = 5.0 / 100;
        } else if (cliente.getTipo().getCod() == 3) {
            taxaJuros = 10.0 / 100;
        }
        return taxaJuros;
    }
}
