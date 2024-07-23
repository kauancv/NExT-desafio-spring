package coelho.kauan.domain.service;

import coelho.kauan.domain.entity.Casa;
import coelho.kauan.domain.repository.CasaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
