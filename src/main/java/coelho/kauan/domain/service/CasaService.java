package coelho.kauan.domain.service;

import coelho.kauan.domain.entity.Casa;
import coelho.kauan.domain.repository.CasaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CasaService {
    private final CasaRepository casaRepository;

    public CasaService(CasaRepository casaRepository) {
        this.casaRepository = casaRepository;
    }

    public void save(Casa casa){
        casaRepository.save(casa);
    }

    public List<Casa> listarTodasCasas(){
        return casaRepository.findAll();
    }

    public List<Casa> buscarViaCep(String cep){
        return casaRepository.findByCep(cep);
    }

    public void deletarCasa (Long id){
        casaRepository.deleteById(id);
    }

    public Optional<Casa> buscarCasa(Long id){
       return casaRepository.findById(id);
    }
}
