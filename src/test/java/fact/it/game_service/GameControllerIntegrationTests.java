package fact.it.game_service;


import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.game_service.model.Game;
import fact.it.game_service.model.GameDTO;
import fact.it.game_service.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.awt.*;

@SpringBootTest() //the class as a class that contains tests
@AutoConfigureMockMvc // sets up theMockMvc object for us to inject
public class GameControllerIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    //regular ReviewRepository connected to a test database
    @Autowired
    private GameRepository gameRepository;

    private   Game g1= new Game();
    private   Game g2= new Game();
    private   Game g3= new Game();
    private   Game g4= new Game();
    public void init() {

    g1.setGameId(1);
    g1.setLevel(1);
    g1.setQuestion("Vraag 1");
    g1.setCorrectanswer("juist");
    g1.setAnswertwo("fout");
    g1.setAnswerthree("fout");
    g1.setObjectName("EXTRA_PotionVial");
    g1.setY(5);
    g1.setX(1);
    g1.setScoreDefensive(10);
    g1.setScoreOffensive(5);



    g2.setGameId(2);
    g2.setLevel(1);
    g2.setQuestion("Vraag 2");
    g2.setCorrectanswer("juist");
    g2.setAnswertwo("fout");
    g2.setAnswerthree("fout");
    g2.setObjectName("DEF_RoundShield");
    g2.setY(1);
    g2.setX(4);
    g2.setScoreDefensive(5);
    g2.setScoreOffensive(0);


    g3.setGameId(3);
    g3.setLevel(2);
    g3.setQuestion("Vraag 3");
    g3.setCorrectanswer("juist");
    g3.setAnswertwo("fout");
    g3.setAnswerthree("fout");
    g3.setObjectName("OFF_Crossbow");
    g3.setY(5);
    g3.setX(4);
    g3.setScoreDefensive(0);
    g3.setScoreOffensive(5);


    g4.setGameId(4);
    g4.setLevel(3);
    g4.setQuestion("Vraag 4");
    g4.setCorrectanswer("juist");
    g4.setAnswertwo("fout");
    g4.setAnswerthree("fout");
    g4.setObjectName("OFF_Pickaxe");
    g4.setY(1.5);
    g4.setX(4);
    g4.setScoreDefensive(0);
    g4.setScoreOffensive(15);


}

private ObjectMapper mapper = new ObjectMapper(); //transform objects to Json



    // Delete ALL data in the test DB
    // Insert testdata
    @BeforeEach
    public void beforeAllTests(){
        gameRepository.deleteAll();
        init();
        gameRepository.save(g1);
        gameRepository.save(g2);
        gameRepository.save(g3);
        gameRepository.save(g4);

    }

    @AfterEach
    public void afterAllTests(){
        gameRepository.deleteAll();
    }




    @Test
    public void givenQuestionId_whenGetQuestionByObjectNam_thenReturnJsonQuestion()throws Exception{
        mockMvc.perform(get("/question/{objectname}","EXTRA_PotionVial"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId",is(1)))
                .andExpect(jsonPath("$.question",is("Vraag 1")))
                .andExpect(jsonPath("$.level",is(1)))
                .andExpect(jsonPath("$.x",is(1.0)))
                .andExpect(jsonPath("$.y",is(5.0)))
                .andExpect(jsonPath("$.correctanswer",is("juist")))
                .andExpect(jsonPath("$.answertwo",is("fout")))
                .andExpect(jsonPath("$.answerthree",is("fout")))
                .andExpect(jsonPath("$.objectName",is("EXTRA_PotionVial")))
                .andExpect(jsonPath("$.scoreDefensive",is(10)))
                .andExpect(jsonPath("$.scoreOffensive",is(5)));
    }

    @Test
    public void givenLevel_whenGetQuestionsByLevel_thenReturnJsonQuestion()throws Exception{
        mockMvc.perform(get("/questionsbylevel/{level}",1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].gameId",is(1)))
                .andExpect(jsonPath("$[0].question",is("Vraag 1")))
                .andExpect(jsonPath("$[0].level",is(1)))
                .andExpect(jsonPath("$[0].x",is(1.0)))
                .andExpect(jsonPath("$[0].y",is(5.0)))
                .andExpect(jsonPath("$[0].correctanswer",is("juist")))
                .andExpect(jsonPath("$[0].answertwo",is("fout")))
                .andExpect(jsonPath("$[0].answerthree",is("fout")))
                .andExpect(jsonPath("$[0].objectName",is("EXTRA_PotionVial")))
                .andExpect(jsonPath("$[0].scoreDefensive",is(10)))
                .andExpect(jsonPath("$[0].scoreOffensive",is(5)))

                .andExpect(jsonPath("$[1].gameId",is(2)))
                .andExpect(jsonPath("$[1].question",is("Vraag 2")))
                .andExpect(jsonPath("$[1].level",is(1)))
                .andExpect(jsonPath("$[1].x",is(4.0)))
                .andExpect(jsonPath("$[1].y",is(1.0)))
                .andExpect(jsonPath("$[1].correctanswer",is("juist")))
                .andExpect(jsonPath("$[1].answertwo",is("fout")))
                .andExpect(jsonPath("$[1].answerthree",is("fout")))
                .andExpect(jsonPath("$[1].objectName",is("DEF_RoundShield")))
                .andExpect(jsonPath("$[1].scoreDefensive",is(5)))
                .andExpect(jsonPath("$[1].scoreOffensive",is(0)));
    }
    @Test
    public void returnJsonQuestions() throws Exception{
        mockMvc.perform(get("/questions"))

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gameId",is(1)))
                .andExpect(jsonPath("$[0].question",is("Vraag 1")))
                .andExpect(jsonPath("$[0].level",is(1)))
                .andExpect(jsonPath("$[0].x",is(1.0)))
                .andExpect(jsonPath("$[0].y",is(5.0)))
                .andExpect(jsonPath("$[0].correctanswer",is("juist")))
                .andExpect(jsonPath("$[0].answertwo",is("fout")))
                .andExpect(jsonPath("$[0].answerthree",is("fout")))
                .andExpect(jsonPath("$[0].objectName",is("EXTRA_PotionVial")))
                .andExpect(jsonPath("$[0].scoreDefensive",is(10)))
                .andExpect(jsonPath("$[0].scoreOffensive",is(5)))

                .andExpect(jsonPath("$[1].gameId",is(2)))
                .andExpect(jsonPath("$[1].question",is("Vraag 2")))
                .andExpect(jsonPath("$[1].level",is(1)))
                .andExpect(jsonPath("$[1].x",is(4.0)))
                .andExpect(jsonPath("$[1].y",is(1.0)))
                .andExpect(jsonPath("$[1].correctanswer",is("juist")))
                .andExpect(jsonPath("$[1].answertwo",is("fout")))
                .andExpect(jsonPath("$[1].answerthree",is("fout")))
                .andExpect(jsonPath("$[1].objectName",is("DEF_RoundShield")))
                .andExpect(jsonPath("$[1].scoreDefensive",is(5)))
                .andExpect(jsonPath("$[1].scoreOffensive",is(0)))

                 .andExpect(jsonPath("$[2].gameId",is(3)))
                .andExpect(jsonPath("$[2].question",is("Vraag 3")))
                .andExpect(jsonPath("$[2].level",is(2)))
                .andExpect(jsonPath("$[2].x",is(4.0)))
                .andExpect(jsonPath("$[2].y",is(5.0)))
                .andExpect(jsonPath("$[2].correctanswer",is("juist")))
                .andExpect(jsonPath("$[2].answertwo",is("fout")))
                .andExpect(jsonPath("$[2].answerthree",is("fout")))
                .andExpect(jsonPath("$[2].objectName",is("OFF_Crossbow")))
                .andExpect(jsonPath("$[2].scoreDefensive",is(0)))
                .andExpect(jsonPath("$[2].scoreOffensive",is(5)))

                          .andExpect(jsonPath("$[3].gameId",is(4)))
                .andExpect(jsonPath("$[3].question",is("Vraag 4")))
                .andExpect(jsonPath("$[3].level",is(3)))
                .andExpect(jsonPath("$[3].x",is(4.0)))
                .andExpect(jsonPath("$[3].y",is(1.5)))
                .andExpect(jsonPath("$[3].correctanswer",is("juist")))
                .andExpect(jsonPath("$[3].answertwo",is("fout")))
                .andExpect(jsonPath("$[3].answerthree",is("fout")))
                .andExpect(jsonPath("$[3].objectName",is("OFF_Pickaxe")))
                .andExpect(jsonPath("$[3].scoreDefensive",is(0)))
                .andExpect(jsonPath("$[3].scoreOffensive",is(15)));
    }
    @Test
    public void whenPostGame_thenReturnJsonGame()throws Exception {
 Game game5= new Game();
        game5.setGameId(5);
        game5.setLevel(10);
        game5.setQuestion("Vraag 5");
        game5.setCorrectanswer("juist");
        game5.setAnswertwo("fout");
        game5.setAnswerthree("fout");
        game5.setObjectName("OFF_Test");
        game5.setY(0);
        game5.setX(5);
        game5.setScoreDefensive(0);
        game5.setScoreOffensive(2);

        mockMvc.perform(post("/question")
                        .content(mapper.writeValueAsString(game5))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId", is(5)))
                .andExpect(jsonPath("$.question", is("Vraag 5")))
                .andExpect(jsonPath("$.level", is(10)))
                .andExpect(jsonPath("$.x", is(5.0)))
                .andExpect(jsonPath("$.y", is(0.0)))
                .andExpect(jsonPath("$.correctanswer", is("juist")))
                .andExpect(jsonPath("$.answertwo", is("fout")))
                .andExpect(jsonPath("$.answerthree", is("fout")))
                .andExpect(jsonPath("$.scoreDefensive", is(0)))
                .andExpect(jsonPath("$.scoreOffensive", is(2)))
                .andExpect(jsonPath("$.objectName", is("OFF_Test")));
    }
    @Test
    public void givenQuestion_whenPutQuestion_thenReturnJsonQuestion() throws Exception{


        GameDTO gameUpdated = new GameDTO();
        gameUpdated.setGameId(1);
        gameUpdated.setLevel(1);
        gameUpdated.setQuestion("Vraag 1");
        gameUpdated.setCorrectanswer("juist");
        gameUpdated.setAnswertwo("fout");
        gameUpdated.setAnswerthree("fout");
        gameUpdated.setObjectName("EXTRA_PotionVialADJUSTED");
        gameUpdated.setY(5.0);
        gameUpdated.setX(1.0);
        gameUpdated.setScoreDefensive(80);
        gameUpdated.setScoreOffensive(4);

        mockMvc.perform(put("/question")
                .content(mapper.writeValueAsString(gameUpdated))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId",is(1)))
                .andExpect(jsonPath("$.question",is("Vraag 1")))
                .andExpect(jsonPath("$.level",is(1)))
                .andExpect(jsonPath("$.x",is(1.0)))
                .andExpect(jsonPath("$.y",is(5.0)))
                .andExpect(jsonPath("$.correctanswer",is("juist")))
                .andExpect(jsonPath("$.answertwo",is("fout")))
                .andExpect(jsonPath("$.answerthree",is("fout")))
                .andExpect(jsonPath("$.objectName",is("EXTRA_PotionVialADJUSTED")))
                .andExpect(jsonPath("$.scoreDefensive",is(80)))
                .andExpect(jsonPath("$.scoreOffensive",is(4)));

    }
    @Test
    public void givenGame_whenDeleteGame_thenStatusOk()throws Exception {

        mockMvc.perform(delete("/question/{number}",1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    public void givenGame_whenDeleteGame_thenStatusNotFound()throws Exception {

        mockMvc.perform(delete("/question/{number}",10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    }

