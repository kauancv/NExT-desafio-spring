package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.ClienteDTO;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.entity.EstadoCivil;
import coelho.kauan.domain.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<?> listarClientes(){
          return ResponseEntity.ok(clienteService.buscarTodosClientes());

    }



}
