package br.com.rodrigoramos.desafiodbc.dto;

import br.com.rodrigoramos.desafiodbc.service.validation.ClienteInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@ClienteInsert
public class ClienteNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=2, max=50, message="O tamanho deve ser entre 2 e 50 caracteres")
    private String nome;

    @NotEmpty(message="Preenchimento obrigatório")
    @Length(min=2, max=50, message="O tamanho deve ser entre 2 e 50 caracteres")
    private String sobrenome;

    @NotEmpty(message="Preenchimento obrigatório")
    private String email;

    @NotEmpty(message="Preenchimento obrigatório")
    private String cpf;


    private Double rendimentoMensal;

    @NotEmpty(message="Preenchimento obrigatório")
    private String numero;

    @NotEmpty(message="Preenchimento obrigatório")
    private String rua;

    @NotEmpty(message="Preenchimento obrigatório")
    private String bairro;

    @NotEmpty(message="Preenchimento obrigatório")
    private String cep;

    public ClienteNewDTO() {
    }

}
