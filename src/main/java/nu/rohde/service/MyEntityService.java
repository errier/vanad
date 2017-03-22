package nu.rohde.service;


import nu.rohde.domain.MyEntity;
import nu.rohde.repository.MyEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyEntityService {

    @Autowired
    MyEntityRepository myEntityRepository;

    /**
     * Find entity bij id
     * @param id The id of the entity
     * @return The entity
     */
    public MyEntity findOne(Long id) {
        return myEntityRepository.findOne(id);
    }

    /**
     * Create an entity
     * @param myEntity The entity to persist
     * @return The persisted entity
     */
    public MyEntity create(MyEntity myEntity) {
        return myEntityRepository.save(myEntity);
    }
}
