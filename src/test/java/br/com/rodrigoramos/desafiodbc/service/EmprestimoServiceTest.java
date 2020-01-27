package br.com.rodrigoramos.desafiodbc.service;

import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;
import br.com.rodrigoramos.desafiodbc.model.enums.TipoCliente;
import br.com.rodrigoramos.desafiodbc.repository.EmprestimoRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmprestimoServiceTest {


    @InjectMocks
    EmprestimoServiceImpl emprestimoService;

    @Mock
    EmprestimoRepository dao;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createEmprestimoTest() {
        Cliente cliente = new Cliente(null, "Jose", "Silva", "jose@gmail.com", "12345678", 1200.10, TipoCliente.TIPO_C);

        Emprestimo emp = new Emprestimo(null, 4, 2000.0, 2800.0, 700, cliente.getId());
        emprestimoService.insert(emp);

        verify(dao, times(1)).save(emp);
    }


    @Test
    public void getEmprestimoByIdTest() {
        when(dao.findByIdCliente(1l)).thenReturn(java.util.Optional.of(new Emprestimo(1l, 4, 2000.0, 2800.0, 700.0, 1l)));
        Emprestimo actual = new Emprestimo(1l, 4, 2000.0, 2800.0, 700.0, 1l);
        Emprestimo emp = emprestimoService.find(1l);

        assertEquals(2800.0, emp.getValorTotal(), 0.001);
    }

}

