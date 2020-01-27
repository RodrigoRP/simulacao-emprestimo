package br.com.rodrigoramos.desafiodbc.service;

import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;
import br.com.rodrigoramos.desafiodbc.model.enums.TipoCliente;
import br.com.rodrigoramos.desafiodbc.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @InjectMocks
    ClienteServiceImpl clienteService;

    @Mock
    ClienteRepository clienteRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getAllClientesTest()
    {
        List<Cliente> list = new ArrayList<Cliente>();
        Cliente c1 = new Cliente(null, "Jose", "Silva", "jose@gmail.com","12345678",1200.10, TipoCliente.TIPO_C);
        Cliente c2 = new Cliente(null, "Pedro", "Silva", "jose@gmail.com","12345678",1500.50, TipoCliente.TIPO_C);
        Cliente c3 = new Cliente(null, "Joana", "Silva", "jose@gmail.com","12345678",2200.40, TipoCliente.TIPO_B);

        list.add(c1);
        list.add(c2);
        list.add(c3);

        when(clienteRepository.findAll()).thenReturn(list);

        List<Cliente> empList = clienteService.findAll();

        assertEquals(3, empList.size());
        verify(clienteRepository, times(1)).findAll();
    }


    @Test
    public void getClienteByIdTest()
    {
        when(clienteRepository.findById(1l)).thenReturn(java.util.Optional.of(new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 1200.10, TipoCliente.TIPO_C)));

        Cliente cliente = clienteService.find(1l);

        assertEquals("Jose", cliente.getNome());
        assertEquals("Silva", cliente.getSobrenome());
        assertEquals("jose@gmail.com", cliente.getEmail());
    }



    @Test
    public void calcularRiscoTest() {
        Cliente cliente = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 1200.10, TipoCliente.TIPO_C);
        Cliente cliente2 = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 2200.10, TipoCliente.TIPO_B);
        Cliente cliente3 = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 9900.10, TipoCliente.TIPO_A);

        cliente = clienteService.calcularRisco(cliente);
        cliente2 = clienteService.calcularRisco(cliente2);
        cliente3= clienteService.calcularRisco(cliente3);

        Assert.assertEquals(TipoCliente.TIPO_C, cliente.getTipo());
        Assert.assertEquals(TipoCliente.TIPO_B, cliente2.getTipo());
        Assert.assertEquals(TipoCliente.TIPO_A, cliente3.getTipo());

    }

    @Test
    public void getJuros() {
        Double taxaJuros1,taxaJuros2,taxaJuros3;
        Cliente cliente = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 1200.10, TipoCliente.TIPO_C);
        Cliente cliente2 = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 2200.10, TipoCliente.TIPO_B);
        Cliente cliente3 = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 9900.10, TipoCliente.TIPO_A);

        taxaJuros1 = clienteService.getJuros(cliente3);
        taxaJuros2 = clienteService.getJuros(cliente2);
        taxaJuros3= clienteService.getJuros(cliente);


        Assert.assertEquals(0.019, taxaJuros1,0.001);
        Assert.assertEquals(0.05, taxaJuros2,0.01);
        Assert.assertEquals(0.1, taxaJuros3,0.01);

    }

    @Test
    public void simularEmprestimo() {
        Cliente cliente = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 1200.10, TipoCliente.TIPO_C);
        clienteService.simularEmprestimo(cliente,2000.0,4);

        Emprestimo emprestimo = clienteService.simularEmprestimo(cliente,2000.0,4);

        Assert.assertEquals(2800.0, emprestimo.getValorTotal(), 0.01);
        Assert.assertEquals(700.0, emprestimo.getValorParcela(), 0.01);


    }


}


