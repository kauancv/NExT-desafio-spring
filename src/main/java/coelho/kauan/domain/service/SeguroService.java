package coelho.kauan.domain.service;

import coelho.kauan.domain.entity.Seguro;
import coelho.kauan.domain.repository.SeguroRepository;
import org.springframework.stereotype.Service;

@Service
public class SeguroService {

    private final SeguroRepository seguroRepository;
    public SeguroService(SeguroRepository seguroRepository) {
        this.seguroRepository = seguroRepository;
    }

    public void save (Seguro seguro) {
        seguroRepository.save(seguro);
    }

}
