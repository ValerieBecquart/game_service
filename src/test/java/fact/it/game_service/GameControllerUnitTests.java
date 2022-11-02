package fact.it.game_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.game_service.model.Game;
import fact.it.game_service.model.GameDTO;
import fact.it.game_service.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.Matchers.is;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest() //configures the class as a class that contains tests
@AutoConfigureMockMvc //sets up the MockMvc object for us to inject
public class GameControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    //uses Mockito to create a ReviewRepository mock which we can fully control as to which response it will give to which method call
    private GameRepository gameRepository;

    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void givenObjectName_whenGetQuestionByObjectName_thenReturnJsonQuestion()throws Exception {
           Game g1= new Game();

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



//The given( ) method allows, you to assign behaviour to the repository mock
    given(gameRepository.findGameByObjectName("EXTRA_PotionVial")).willReturn(g1);

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
    public void whenPostGame_thenReturnJsonGame()throws Exception {
        GameDTO game5 = new GameDTO();
        game5.setGameId(5);
        game5.setLevel(10);
        game5.setQuestion("vraag 5");
        game5.setCorrectanswer("juist");
        game5.setAnswertwo("fout");
        game5.setAnswerthree("fout");
        game5.setObjectName("OFF_Test");
        game5.setY(1);
        game5.setX(8);
        game5.setScoreDefensive(0);
        game5.setScoreOffensive(2);



        mockMvc.perform(post("/question")
                        .content(mapper.writeValueAsString(game5))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId", is(5)))
                .andExpect(jsonPath("$.question", is("vraag 5")))
                .andExpect(jsonPath("$.level", is(10)))
                .andExpect(jsonPath("$.x", is(8.0)))
                .andExpect(jsonPath("$.y", is(1.0)))
                .andExpect(jsonPath("$.correctanswer", is("juist")))
                .andExpect(jsonPath("$.answertwo", is("fout")))
                .andExpect(jsonPath("$.answerthree", is("fout")))
                .andExpect(jsonPath("$.scoreDefensive", is(0)))
                .andExpect(jsonPath("$.scoreOffensive", is(2)))
                .andExpect(jsonPath("$.objectName", is("OFF_Test")));
    }




@Test
public void givenquestion_whenPutQuestion_thenReturnJsonQuestion() throws Exception{
    Game g1 = new Game();
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


    given(gameRepository.findGameByGameId(1)).willReturn(g1);

    GameDTO g1update = new GameDTO();
    g1update.setGameId(1);
    g1update.setLevel(1);
    g1update.setQuestion("Vraag 1");
    g1update.setCorrectanswer("juist");
    g1update.setAnswertwo("fout");
    g1update.setAnswerthree("fout");
    g1update.setObjectName("EXTRA_PotionVialADJUSTED");
    g1update.setY(5);
    g1update.setX(1);
    g1update.setScoreDefensive(10);
    g1update.setScoreOffensive(5);


    mockMvc.perform(put("/question")
            .content(mapper.writeValueAsString(g1update))
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
            .andExpect(jsonPath("$.scoreDefensive",is(10)))
            .andExpect(jsonPath("$.scoreOffensive",is(5)));
    }


    @Test
    public void givenGame_whenDeleteGame_thenStatusOk()throws Exception {
        Game g1= new Game();

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

        given(gameRepository.findGameByGameId(1)).willReturn(g1);

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


