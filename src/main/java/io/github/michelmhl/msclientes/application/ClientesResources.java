package io.github.michelmhl.msclientes.application;

import io.github.michelmhl.msclientes.domain.dto.ClienteSaveRequest;
import io.github.michelmhl.msclientes.application.services.ClienteService;
import io.github.michelmhl.msclientes.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClientesResources {


    public final ClienteService service;

    @GetMapping
    public String status() {
        log.info("obtendo o status do microservices de clientes ");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteSaveRequest request) {
        Cliente cliente = request.toModel();
        service.save(cliente);
        URI headerLocation =
                ServletUriComponentsBuilder
                        .fromCurrentRequest().query("cpf={cpf}")
                        .buildAndExpand(cliente.getCpf())
                        .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

@GetMapping(params = "cpf")
    public ResponseEntity dadosCliete(@RequestParam("cpf") String cpf){
        Optional<Cliente> cliente = service.getByCliente(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }
    @DeleteMapping(value = "/{cpf}")
    public ResponseEntity<String> delete(@PathVariable String cpf) {
        try {
            service.delete(cpf);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            // Aqui você pode lidar com a exceção conforme necessário,
            // como logá-la ou retornar um ResponseEntity com status de erro.
            return ResponseEntity.status(500).body("Erro durante a exclusão: " + ex.getMessage());
        }
    }

//    @GetMapping
//    public ResponseEntity<List<Cliente>> getAllClientes() {
//        List<Cliente> clientes = service.getAllClientes();
//        return ResponseEntity.ok().body(clientes);
//    }
}
