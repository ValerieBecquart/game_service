package fact.it.game_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.game_service.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest //configures the class as a class that contains tests
@AutoConfigureMockMvc //sets up the MockMvc object for us to inject
public class GameControllerUnitTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    //uses Mockito to create a ReviewRepository mock which we can fully control as to which response it will give to which method call
//    private GameRepository gameRepository;
//
//    private ObjectMapper mapper = new ObjectMapper();
//    @Test
//    public void tospecify(){}
}
