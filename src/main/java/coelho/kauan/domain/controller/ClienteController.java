package coelho.kauan.domain.controller;

import coelho.kauan.api.dto.ClienteDTO;
import coelho.kauan.domain.entity.Cliente;
import coelho.kauan.domain.entity.EstadoCivil;
import coelho.kauan.domain.service.ClienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/clientes")
    public void cadastroCliente (@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(clienteDTO);
        clienteService.cadastrarCliente(cliente);
    }



}
