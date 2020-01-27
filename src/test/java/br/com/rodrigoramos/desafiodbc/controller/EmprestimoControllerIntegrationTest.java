package br.com.rodrigoramos.desafiodbc.controller;

import br.com.rodrigoramos.desafiodbc.DesafioDbcApplication;
import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.Emprestimo;
import br.com.rodrigoramos.desafiodbc.model.enums.TipoCliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesafioDbcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmprestimoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1";
    }

    @Test
    public void contextLoads() {

    }


    @Test
    public void testGetAllEmprestimos() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/emprestimos",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEmprestimoByClienteId() {
        Emprestimo emprestimo = restTemplate.getForObject(getRootUrl() + "/emprestimos/1", Emprestimo.class);
        System.out.println(emprestimo.getValorTotal());
        assertNotNull(emprestimo);
    }

    @Test
    public void testCreateEmprestimo() {
        Cliente cliente = new Cliente(1l, "Jose", "Silva", "jose@gmail.com", "12345678", 1200.10, TipoCliente.TIPO_C);
        Emprestimo emprestimo = new Emprestimo(1l, 4, 2000.0, 2800.0, 700, cliente.getId());

        ResponseEntity<Emprestimo> postResponse = restTemplate.postForEntity(getRootUrl() + "/emprestimos", emprestimo, Emprestimo.class);
        assertNotNull(postResponse);
       // assertNotNull(postResponse.getBody());
    }

}
