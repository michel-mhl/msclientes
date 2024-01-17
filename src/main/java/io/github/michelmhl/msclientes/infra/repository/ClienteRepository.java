package io.github.michelmhl.msclientes.infra.repository;

import io.github.michelmhl.msclientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cliente e WHERE e.cpf = :cpf")
    void deleteByCpf(String cpf);


}
