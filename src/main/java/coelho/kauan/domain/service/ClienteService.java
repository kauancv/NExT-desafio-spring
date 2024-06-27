package coelho.kauan.domain.service;

import coelho.kauan.domain.repository.ClienteRepository;
import coelho.kauan.domain.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }


}
