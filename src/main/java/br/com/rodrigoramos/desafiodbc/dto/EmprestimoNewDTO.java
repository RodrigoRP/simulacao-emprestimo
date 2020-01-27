package br.com.rodrigoramos.desafiodbc.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmprestimoNewDTO {

    @NotEmpty(message="Preenchimento obrigatório")
    private Integer nroParcelas;

    @NotEmpty(message="Preenchimento obrigatório")
    private double valorRequerido;

    @NotEmpty(message="Preenchimento obrigatório")
    private Long idCliente;

}
