package br.com.rodrigoramos.desafiodbc.controller;

import br.com.rodrigoramos.desafiodbc.DesafioDbcApplication;
import br.com.rodrigoramos.desafiodbc.model.Cliente;
import br.com.rodrigoramos.desafiodbc.model.enums.TipoCliente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DesafioDbcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port +"/api/v1";
    }

    @Test
    public void contextLoads() {

    }


    @Test
    public void testGetAllClientes() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/clientes",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetClienteById() {
        Cliente cliente = restTemplate.getForObject(getRootUrl() + "/clientes/1", Cliente.class);
        System.out.println(cliente.getNome());
        assertNotNull(cliente);
    }

    @Test
    public void testCreateCliente() {
        Cliente cliente = new Cliente(null, "Jose", "Silva", "jose@gmail.com","12345678",1200.10, TipoCliente.TIPO_C);

        ResponseEntity<Cliente> postResponse = restTemplate.postForEntity(getRootUrl() + "/clientes", cliente, Cliente.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateCliente() {
        int id = 1;
        Cliente cliente = restTemplate.getForObject(getRootUrl() + "/clientes/" + id, Cliente.class);
        cliente.setNome("admin1");
        cliente.setSobrenome("admin2");

        restTemplate.put(getRootUrl() + "/clientes/" + id, cliente);

        Cliente updatedCliente = restTemplate.getForObject(getRootUrl() + "/clientes/" + id, Cliente.class);
        assertNotNull(updatedCliente);
    }

    @Test
    public void testDeleteCliente() {
        int id = 2;
        Cliente cliente = restTemplate.getForObject(getRootUrl() + "/clientes/" + id, Cliente.class);
        assertNotNull(cliente);

        restTemplate.delete(getRootUrl() + "/clientes/" + id);

        try {
            cliente = restTemplate.getForObject(getRootUrl() + "/clientes/" + id, Cliente.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
