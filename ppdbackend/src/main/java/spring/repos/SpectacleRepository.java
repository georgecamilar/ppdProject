package spring.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.model.Spectacol;

@Repository
public interface SpectacleRepository extends CrudRepository<Spectacol, Integer> {
}
