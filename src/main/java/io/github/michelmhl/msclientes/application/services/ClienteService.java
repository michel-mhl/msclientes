package io.github.michelmhl.msclientes.application.services;

import io.github.michelmhl.msclientes.domain.Cliente;
import io.github.michelmhl.msclientes.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    @Autowired
    private ClienteRepository repository;


    @Transactional
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> getByCliente(String cpf){
        return repository.findByCpf(cpf);
    }

    public void delete(String cpf) {
        repository.deleteByCpf(cpf);
    }

    public List<Cliente> getAllClientes() {
       return repository.findAll();
    }
}
