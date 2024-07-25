package coelho.kauan.domain.service;

import coelho.kauan.domain.controller.VeiculoController;
import coelho.kauan.domain.entity.Veiculo;
import coelho.kauan.domain.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoService {
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public void salvar (Veiculo veiculo) {
        veiculoRepository.save(veiculo);
    }

    public Optional<Veiculo> buscarVeiculo (Long id) {
      return  veiculoRepository.findById(id);
    }

    public void deletarVeiculo (Long id) {
        veiculoRepository.deleteById(id);
    }
}

