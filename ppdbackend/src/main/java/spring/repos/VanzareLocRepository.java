package spring.repos;

import spring.model.VanzareLoc;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VanzareLocRepository extends CrudRepository<VanzareLoc, Integer> {
}
