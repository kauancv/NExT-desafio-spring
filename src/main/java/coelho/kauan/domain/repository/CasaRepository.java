package coelho.kauan.domain.repository;

import coelho.kauan.domain.entity.Casa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasaRepository extends JpaRepository<Casa,Long> {
    public List<Casa> findByCep (String cep);
}
