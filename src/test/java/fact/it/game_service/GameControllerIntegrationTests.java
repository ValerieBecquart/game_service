package fact.it.game_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.game_service.model.Game;
import fact.it.game_service.repository.GameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest //the class as a class that contains tests
@AutoConfigureMockMvc // sets up theMockMvc object for us to inject
public class GameControllerIntegrationTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    //regular ReviewRepository connected to a test database
//    @Autowired
//    private GameRepository gameRepository;
//
//
//    private Game game1 = new Game(1,"Question 1", "Theme 1",1,1, "Answer correct", "Answer faulty 1", "Answer faulty 2");
//    private Game game2 = new Game(2,"Question 2", "Theme 1",1,1, "Answer correct", "Answer faulty 1", "Answer faulty 2");
//    private Game game3 = new Game(3,"Question 3", "Theme 2",2,2, "Answer correct", "Answer faulty 1", "Answer faulty 2");
//    private Game game4 = new Game(4,"Question 4", "Theme 2",3,3, "Answer correct", "Answer faulty 1", "Answer faulty 2");
//
//    // Delete ALL data in the test DB
//    // Insert testdata
//    @BeforeEach
//    public void beforeAllTests(){
//        gameRepository.deleteAll();
//        gameRepository.save(game1);
//        gameRepository.save(game2);
//        gameRepository.save(game3);
//        gameRepository.save(game4);
//
//    }
//
//    @AfterEach
//    public void afterAllTests(){
//        gameRepository.deleteAll();
//    }
//
//    //r transforms objects to Json strings
//    private ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void toBeDefined(){};

}
