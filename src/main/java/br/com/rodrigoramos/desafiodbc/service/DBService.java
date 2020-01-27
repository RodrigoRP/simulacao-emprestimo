package br.com.rodrigoramos.desafiodbc.service;

import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.Endereco;
import br.com.rodrigoramos.desafiodbc.model.enums.TipoCliente;
import br.com.rodrigoramos.desafiodbc.repository.ClienteRepository;
import br.com.rodrigoramos.desafiodbc.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    private ClienteRepository clienteRepository;
    private EnderecoRepository enderecoRepository;

    @Autowired
    public DBService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public void instantiateTestDatabase() throws ParseException {

        Cliente c1 = new Cliente(null, "Jose", "Silva", "jose@gmail.com","12345678",1200.10, TipoCliente.TIPO_C);
        Cliente c2 = new Cliente(null, "Pedro", "Silva", "jose@gmail.com","12345678",1500.50, TipoCliente.TIPO_C);
        Cliente c3 = new Cliente(null, "Joana", "Silva", "jose@gmail.com","12345678",2200.40, TipoCliente.TIPO_B);
        Cliente c4 = new Cliente(null, "Carlos","Silva", "jose@gmail.com","12345678", 11200.50, TipoCliente.TIPO_C);
        Cliente c5 = new Cliente(null, "Maria", "Silva", "jose@gmail.com","12345678",2500.89, TipoCliente.TIPO_B);
        Cliente c6 = new Cliente(null, "Ze", "Silva", "jose@gmail.com","12345678",3200.20, TipoCliente.TIPO_B);

        Endereco e1 = new Endereco(null, "15", "Casa", "Centro", "98000", c1);
        Endereco e2 = new Endereco(null, "25", "Apto", "Bonfim", "94000", c2);
        Endereco e3 = new Endereco(null, "35", "Casa", "Salgado", "93000", c3);
        Endereco e4 = new Endereco(null, "45", "Apto", "Sao Jose", "92000", c4);
        Endereco e5 = new Endereco(null, "55", "Casa", "Medianeira", "198000", c5);
        Endereco e6 = new Endereco(null, "65", "Apto", "Jardim Botanico", "28000", c6);

        c1.getEnderecos().addAll(Arrays.asList(e1));
        c2.getEnderecos().addAll(Arrays.asList(e1));
        c3.getEnderecos().addAll(Arrays.asList(e1));
        c4.getEnderecos().addAll(Arrays.asList(e1));
        c5.getEnderecos().addAll(Arrays.asList(e1));
        c6.getEnderecos().addAll(Arrays.asList(e1));

        clienteRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));
        enderecoRepository.saveAll(Arrays.asList(e1,e2,e3,e4,e5,e6));

    }
}
