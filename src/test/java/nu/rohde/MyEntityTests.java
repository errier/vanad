package nu.rohde;

import nu.rohde.domain.MyEntity;
import nu.rohde.repository.MyEntityRepository;
import nu.rohde.service.MyEntityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VanadApplication.class})
public class MyEntityTests {

	@Autowired
	MyEntityService myEntityService;

	@Autowired
	MyEntityRepository myEntityRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setup() {
		myEntityRepository.deleteAll();
		jdbcTemplate.execute("ALTER TABLE MY_ENTITY ALTER COLUMN ID RESTART WITH 1");
	}

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void entityHasNoName() {
		myEntityService.create(new MyEntity(null, 99, new Date()));
	}

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void entityHasInvalidName() {
		myEntityService.create(new MyEntity("85^!%^!", 99, new Date()));
	}

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void entityHasInvalidAge() {
		myEntityService.create(new MyEntity("Test", -1, new Date()));
	}

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void entityHasInvalidDate() {
		myEntityService.create(new MyEntity("Test", -1, null));
	}

	@Test
	public void persistedEntityShouldHaveId1() {
		MyEntity myEntity = myEntityService.create(new MyEntity("Test", 99, new Date()));
		Assert.assertEquals("Id not equal to 1", 1, myEntity.getId());
	}

	@Test
	public void getEntityById1() {
		myEntityService.create(new MyEntity("Test", 99, new Date()));
		MyEntity myEntity = myEntityService.findOne(1L);
		Assert.assertNotNull("Entity with id 1 not found", myEntity);
	}
}
