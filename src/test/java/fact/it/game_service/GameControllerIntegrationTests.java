package fact.it.game_service;


import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.game_service.model.Game;
import fact.it.game_service.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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

    private Game game1 = new Game(1,"vraag 1", 1, 123.45, 567.89, "juist", "fout", "fout",4,4,"EXTRA_PotionVial");
    private Game game2 = new Game(2,"vraag 2", 1, 150, 50, "juist", "fout", "fout",5,0,"DEF_RoundShield");
    private Game game3 = new Game(3,"vraag 3",2 , 124.45, 568.89, "juist", "fout", "fout",0,10,"OFF_Crossbow");
    private Game game4 = new Game(4,"vraag 4",3, 122.45, 566.89, "juist", "fout", "fout",0,2,"OFF_Pickaxe");

private ObjectMapper mapper = new ObjectMapper(); //transform objects to Json



    // Delete ALL data in the test DB
    // Insert testdata
    @BeforeEach
    public void beforeAllTests(){
        gameRepository.deleteAll();
        gameRepository.save(game1);
        gameRepository.save(game2);
        gameRepository.save(game3);
        gameRepository.save(game4);

    }

    @AfterEach
    public void afterAllTests(){
        gameRepository.deleteAll();
    }




    @Test
    public void givenQuestionId_whenGetQuestionByGameId_thenReturnJsonQuestion()throws Exception{
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
    public void givenLevel_whenGetQuestionsByLevel_thenReturnJsonQuestion()throws Exception{
        mockMvc.perform(get("/questionsbylevel/{level}",1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].gameId",is(1)))
                .andExpect(jsonPath("$[0].question",is("vraag 1")))
                .andExpect(jsonPath("$[0].level",is(1)))
                .andExpect(jsonPath("$[0].x",is(123.45)))
                .andExpect(jsonPath("$[0].y",is(567.89)))
                .andExpect(jsonPath("$[0].correctanswer",is("juist")))
                .andExpect(jsonPath("$[0].answertwo",is("fout")))
                .andExpect(jsonPath("$[0].answerthree",is("fout")))
                .andExpect(jsonPath("$[0].objectName",is("EXTRA_PotionVial")))
                .andExpect(jsonPath("$[0].scoreDefensive",is(4)))
                .andExpect(jsonPath("$[0].scoreOffensive",is(4)))

                .andExpect(jsonPath("$[1].gameId",is(2)))
                .andExpect(jsonPath("$[1].question",is("vraag 2")))
                .andExpect(jsonPath("$[1].level",is(1)))
                .andExpect(jsonPath("$[1].x",is(150.0)))
                .andExpect(jsonPath("$[1].y",is(50.0)))
                .andExpect(jsonPath("$[1].correctanswer",is("juist")))
                .andExpect(jsonPath("$[1].answertwo",is("fout")))
                .andExpect(jsonPath("$[1].answerthree",is("fout")))
                .andExpect(jsonPath("$[1].objectName",is("DEF_RoundShield")))
                .andExpect(jsonPath("$[1].scoreDefensive",is(5)))
                .andExpect(jsonPath("$[1].scoreOffensive",is(0)));
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
    public void givenQuestion_whenPutQuestion_thenReturnJsonQuestion() throws Exception{


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
    }

