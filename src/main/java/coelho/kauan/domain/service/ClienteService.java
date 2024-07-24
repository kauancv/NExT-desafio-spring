package coelho.kauan.domain.service;

import coelho.kauan.domain.repository.ClienteRepository;
import coelho.kauan.domain.entity.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public void save(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarTodosClientes() {
       return clienteRepository.findAll();
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}
