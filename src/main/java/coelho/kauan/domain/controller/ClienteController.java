package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.ClienteDTO;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public void cadastroCliente (@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(clienteDTO);
        clienteService.save(cliente);
    }

    @GetMapping
    public ResponseEntity<?> listarClientes(){
          return ResponseEntity.ok(clienteService.buscarTodosClientes());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCliente (@PathVariable Long id){
        Optional<Cliente> clienteEncontrado = clienteService.buscarClientePorId(id);
        if (clienteEncontrado.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body(clienteEncontrado.get());
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não Encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente (@PathVariable Long id, @RequestBody ClienteDTO clienteDTO){
        Optional<Cliente> clienteBuscado = clienteService.buscarClientePorId(id);
        if (clienteBuscado.isPresent()){
            Cliente cliente = clienteBuscado.get();
             cliente.setNome(clienteDTO.getNome());
             cliente.setIdade(clienteDTO.getIdade());
             cliente.setDependentes(cliente.getDependentes());
             cliente.setRenda(clienteDTO.getRenda());
             cliente.setData_atualizacao(LocalDate.now());

             clienteService.save(cliente);

             return ResponseEntity.ok().body(cliente);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente não Encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public void deletarCliente (@PathVariable Long id){
        clienteService.deletarCliente(id);
    }



}
