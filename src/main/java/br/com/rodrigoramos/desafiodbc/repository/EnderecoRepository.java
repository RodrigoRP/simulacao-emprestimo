package br.com.rodrigoramos.desafiodbc.repository;

import br.com.rodrigoramos.desafiodbc.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
