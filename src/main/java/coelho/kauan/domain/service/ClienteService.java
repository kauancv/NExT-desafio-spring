package coelho.kauan.domain.service;

import coelho.kauan.domain.entity.Casa;
import coelho.kauan.domain.repository.ClienteRepository;
import coelho.kauan.domain.entity.Cliente;
import org.springframework.stereotype.Service;

import java.lang.ref.Cleaner;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public void cadastrarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarTodosClientes() {
       return clienteRepository.findAll();
    }



}
