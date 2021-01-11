package spring.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import spring.model.Spectacol;

@Repository
public interface SpectacolRepository extends CrudRepository<Spectacol, Integer> {
}
