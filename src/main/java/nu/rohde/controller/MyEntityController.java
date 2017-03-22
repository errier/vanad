package nu.rohde.controller;

import nu.rohde.domain.MyEntity;
import nu.rohde.exception.MyEntityNotFoundException;
import nu.rohde.service.MyEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/entities")
public class MyEntityController {

    @Autowired
    MyEntityService myEntityService;

    /**
     * Get entity
     * @param id id of entity in database
     * @return requested entity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MyEntity get(@PathVariable(name = "id") long id) {

        MyEntity myEntity = myEntityService.findOne(id);
        if (myEntity == null) {
            throw new MyEntityNotFoundException();
        }
        return myEntity;
    }

    /**
     * Persist entity
     * @param myEntity persist validated json representation of MyEntity to database
     * @return persisted entity
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public MyEntity create(@Valid @RequestBody MyEntity myEntity) {
        return myEntityService.create(myEntity);
    }
}
