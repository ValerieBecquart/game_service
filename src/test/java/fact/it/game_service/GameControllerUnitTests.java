package fact.it.game_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.game_service.model.Game;
import fact.it.game_service.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//@SpringBootTest() //configures the class as a class that contains tests
//@AutoConfigureMockMvc //sets up the MockMvc object for us to inject
public class GameControllerUnitTests {
    /*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    //uses Mockito to create a ReviewRepository mock which we can fully control as to which response it will give to which method call
    private GameRepository gameRepository;

    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void givenQId_whenGetQuestionByGameId_thenReturnJsonQuestion()throws Exception {
         Game game1 = new Game(1,"vraag 1", 1, 123.45, 567.89, "juist", "fout", "fout",4,4,"EXTRA_PotionVial");
         Game game2 = new Game(2,"vraag 2", 1, 150, 50, "juist", "fout", "fout",5,0,"DEF_RoundShield");



//The given( ) method allows, you to assign behaviour to the repository mock
    given(gameRepository.findGameByGameId(1)).willReturn(game1);

        mockMvc.perform(get("/question/{gameId}",1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId",is(1)))
                .andExpect(jsonPath("$.question",is("vraag 1")))
                .andExpect(jsonPath("$.level",is(1)))
                .andExpect(jsonPath("$.x",is(123.45)))
                .andExpect(jsonPath("$.y",is(567.89)))
                .andExpect(jsonPath("$.correctanswer",is("juist")))
                .andExpect(jsonPath("$.answertwo",is("fout")))
                .andExpect(jsonPath("$.answerthree",is("fout")))
                .andExpect(jsonPath("$.objectName",is("EXTRA_PotionVial")))
                .andExpect(jsonPath("$.scoreDefensive",is(4)))
                .andExpect(jsonPath("$.scoreOffensive",is(4)));
    }
    @Test
    public void whenPostGame_thenReturnJsonGame()throws Exception {
        Game game5 = new Game(5, "vraag 5", 10, 8, 1, "juist", "fout", "fout", 0, 2, "OFF_Test");

        mockMvc.perform(post("/question")
                        .content(mapper.writeValueAsString(game5))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId", is(5)))
                .andExpect(jsonPath("$.question", is("vraag 5")))
                .andExpect(jsonPath("$.level", is(10)))
                .andExpect(jsonPath("$.x", is(8)))
                .andExpect(jsonPath("$.y", is(1)))
                .andExpect(jsonPath("$.correctanswer", is("juist")))
                .andExpect(jsonPath("$.answertwo", is("fout")))
                .andExpect(jsonPath("$.answerthree", is("fout")))
                .andExpect(jsonPath("$.scoreDefensive", is(0)))
                .andExpect(jsonPath("$.scoreOffensive", is(0)))
                .andExpect(jsonPath("$.objectName", is("OFF_Test")));
    }




@Test
public void givenquestion_whenPutQuestion_thenReturnJsonQuestion() throws Exception{
    Game game1 = new Game(1,"vraag 1", 1, 123.45, 567.89, "juist", "fout", "fout",4,4,"EXTRA_PotionVial");
    given(gameRepository.findGameByGameId(1)).willReturn(game1);

    Game gameUpdated = new Game(1,"vraag 1", 1, 123.45, 567.89, "juist", "fout", "fout",4,4,"EXTRA_PotionVialADJUSTED");

    mockMvc.perform(put("/question")
            .content(mapper.writeValueAsString(gameUpdated))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.gameId",is(1)))
            .andExpect(jsonPath("$.question",is("vraag 1")))
            .andExpect(jsonPath("$.level",is(1)))
            .andExpect(jsonPath("$.x",is(123.45)))
            .andExpect(jsonPath("$.y",is(567.89)))
            .andExpect(jsonPath("$.correctanswer",is("juist")))
            .andExpect(jsonPath("$.answertwo",is("fout")))
            .andExpect(jsonPath("$.answerthree",is("fout")))
            .andExpect(jsonPath("$.objectName",is("EXTRA_PotionVialADJUSTED")))
            .andExpect(jsonPath("$.scoreDefensive",is(4)))
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

     */
    }


