package nu.rohde.repository;

import nu.rohde.domain.MyEntity;
import org.springframework.data.repository.CrudRepository;

public interface MyEntityRepository  extends CrudRepository<MyEntity, Long> {
}
