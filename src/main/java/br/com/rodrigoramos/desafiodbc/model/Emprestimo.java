package br.com.rodrigoramos.desafiodbc.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Emprestimo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer nroParcelas;
    private double valorRequerido;
    private double valorTotal;
    private double valorParcela;
    private Long idCliente;

    public Emprestimo() {
    }

    public Emprestimo(Long id, Integer nroParcelas, double valorRequerido, double valorTotal, double valorParcela, Long idCliente) {
        this.id = id;
        this.nroParcelas = nroParcelas;
        this.valorRequerido = valorRequerido;
        this.valorTotal = valorTotal;
        this.valorParcela = valorParcela;
        this.idCliente = idCliente;
    }



}
