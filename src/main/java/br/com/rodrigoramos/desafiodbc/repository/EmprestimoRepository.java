package br.com.rodrigoramos.desafiodbc.repository;

import br.com.rodrigoramos.desafiodbc.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    Optional<Emprestimo> findByIdCliente(Long id);
}
