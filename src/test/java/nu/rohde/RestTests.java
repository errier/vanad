package nu.rohde;

import nu.rohde.domain.MyEntity;
import nu.rohde.repository.MyEntityRepository;
import nu.rohde.service.MyEntityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {VanadApplication.class})
@WebAppConfiguration
public class RestTests {

    private MockMvc mockMvc;

    @Autowired
    private MyEntityService myEntityService;

    @Autowired
    private MyEntityRepository myEntityRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setup() throws Exception {
        myEntityRepository.deleteAll();
        jdbcTemplate.execute("ALTER TABLE MY_ENTITY ALTER COLUMN ID RESTART WITH 1");

        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void entityNotFound() throws Exception {
        mockMvc.perform(get("/entities/17346573465"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void entityFound() throws Exception {
        MyEntity myEntity = myEntityService.create(new MyEntity("Test", 45, new Date()));
        mockMvc.perform(get("/entities/" + myEntity.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void createEntitySuccess() throws Exception {
        String json = "{\"name\": \"Test\", \"age\": 45, \"birthday\": \"1971-11-13\"}";
        mockMvc.perform(post("/entities/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        assertNotNull("Entity with id 1 not persisted", myEntityRepository.findOne(1L));
    }

    @Test
    public void createEntityFails() throws Exception {
        String json = "{\"name\": \"Test\", \"age\": -45, \"birthday\": \"1971-11-13\"}";
        mockMvc.perform(post("/entities/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().is(400));
        assertNull("Entity with id 1 is persisted unintentionally", myEntityRepository.findOne(1L));
    }
}
