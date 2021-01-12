package spring.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.model.Sala;

@Repository
public interface SalaRepository extends CrudRepository<Sala, Integer> {}
