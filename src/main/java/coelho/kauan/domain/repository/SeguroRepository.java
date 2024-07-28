package coelho.kauan.domain.repository;

import coelho.kauan.domain.entity.Seguro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguroRepository extends JpaRepository<Seguro,Long> {
}
