package fact.it.game_service;


import fact.it.game_service.model.Game;
import fact.it.game_service.repository.GameRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.awt.*;

@SpringBootTest //the class as a class that contains tests
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
    public void givenQId_whenGetQuestionById_thenReturnJsonQuestion()throws Exception{
        mockMvc.perform(get("/question/{id}",1))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
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

                .andExpect(jsonPath("$[1].question",is("vraag 2")))
                .andExpect(jsonPath("$[1].level",is(1)))
                .andExpect(jsonPath("$[1].x",is(150)))
                .andExpect(jsonPath("$[1].y",is(50)))
                .andExpect(jsonPath("$[1].correctanswer",is("juist")))
                .andExpect(jsonPath("$[1].answertwo",is("fout")))
                .andExpect(jsonPath("$[1].answerthree",is("fout")))
                .andExpect(jsonPath("$[1].objectName",is("DEF_RoundShield")))
                .andExpect(jsonPath("$[1].scoreDefensive",is(5)))
                .andExpect(jsonPath("$[1].scoreOffensive",is(0)));
    }






}
