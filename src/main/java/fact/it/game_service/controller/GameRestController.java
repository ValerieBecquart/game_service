package fact.it.game_service.controller;

import fact.it.game_service.model.Game;
import fact.it.game_service.model.GameDTO;
import fact.it.game_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.PostConstruct;
import java.util.List;

import java.util.Optional;


@RestController
public class GameRestController {

    @Autowired
    private GameRepository gameRepository;

    @PostConstruct
    public void fillDB(){
String juist="juist";
String fout="fout";
        if (gameRepository.count() == 0){

            Game g1= new Game();
            g1.setGameId(1);
            g1.setLevel(1);
            g1.setQuestion("Wat is de naam van het explosief dat de Lannisters gebruikte in 'The Battle of Blackwater'?");
            g1.setCorrectanswer("Wildfire");
            g1.setAnswertwo("Dragonfire");
            g1.setAnswerthree("Godsfire");
            g1.setObjectName("EXTRA_PotionVial");
            g1.setY(5);
            g1.setX(1);
            g1.setScoreDefensive(10);
            g1.setScoreOffensive(5);


            Game g2= new Game();
            g2.setGameId(2);
            g2.setLevel(1);
            g2.setQuestion("Welke militaire orde beschermt het koninkrijk tegen bedreigingen uit het Noorden?");
            g2.setCorrectanswer("The Night's Watch");
            g2.setAnswertwo("The Iron Fleet");
            g2.setAnswerthree("The Knight Of The Vale");
            g2.setObjectName("DEF_RoundShield");
            g2.setY(1);
            g2.setX(4);
            g2.setScoreDefensive(5);
            g2.setScoreOffensive(0);

            Game g3= new Game();
            g3.setGameId(3);
            g3.setLevel(2);
            g3.setQuestion("Wat is de naam van de grote kruisboog die er niet in slaagde om Kings's Landing te beschermen tegen de draken");
            g3.setCorrectanswer("Scorpion");
            g3.setAnswertwo("Millipede");
            g3.setAnswerthree("Mantis");
            g3.setObjectName("OFF_Crossbow");
            g3.setY(5);
            g3.setX(4);
            g3.setScoreDefensive(0);
            g3.setScoreOffensive(5);

            Game g4= new Game();
            g4.setGameId(4);
            g4.setLevel(3);
            g4.setQuestion("Wat is de naam van Arya haar zwaard");
            g4.setCorrectanswer("Needle");
            g4.setAnswertwo("Pointy");
            g4.setAnswerthree("Fang");
            g4.setObjectName("OFF_Pickaxe");
            g4.setY(1.5);
            g4.setX(4);
            g4.setScoreDefensive(0);
            g4.setScoreOffensive(15);
            
            
            gameRepository.save(g1);
            gameRepository.save(g2);
            gameRepository.save(g3);
            gameRepository.save(g4);
        }


    }

    //get all questions
    @GetMapping("/questions")
    public List<Game> getAllQuestions(){

        return gameRepository.findAll();
    }

    //Get question by object name
    @GetMapping("/question/{objectname}")
    public Game getQuestionByObjectName(@PathVariable String objectname){

        return gameRepository.findGameByObjectName(objectname);
    }
    @GetMapping("/questionsbylevel/{level}")
    public List<Game> getAllQuestionsByLevel(@PathVariable int level){

        return gameRepository.findAllByLevelIs(level);
    }

    @GetMapping("/highestlevel")
    public Game getHighestLevel(){
       return gameRepository.findFirstByOrderByLevelDesc();
    }

    //POST: question
    @PostMapping("/question")
    public Game createQuestion(@RequestBody GameDTO question){


        Game peristentQuestion = new Game();
        peristentQuestion.setGameId(question.getGameId());
        peristentQuestion.setQuestion(question.getQuestion());
        peristentQuestion.setLevel(question.getLevel());
        peristentQuestion.setX(question.getX());
        peristentQuestion.setY(question.getY());
        peristentQuestion.setScoreOffensive(question.getScoreOffensive());
        peristentQuestion.setScoreDefensive(question.getScoreDefensive());
        peristentQuestion.setAnswertwo(question.getAnswertwo());
        peristentQuestion.setAnswerthree(question.getAnswerthree());
        peristentQuestion.setCorrectanswer(question.getCorrectanswer());
        peristentQuestion.setObjectName(question.getObjectName());
        gameRepository.save(peristentQuestion);
        return peristentQuestion;
    }

    //PUT:
    @PutMapping("/question")
    public ResponseEntity<Void> updateQuestion(@RequestBody GameDTO questionDetails) {
        Optional<Game> gameFetch = Optional.ofNullable(gameRepository.findGameByGameId(questionDetails.getGameId()));
        if (gameFetch.isPresent()) {
            Game game = new Game();
            game.setQuestion(questionDetails.getQuestion());
            game.setLevel(questionDetails.getLevel());
            game.setX(questionDetails.getX());
            game.setY(questionDetails.getY());
            game.setCorrectanswer(questionDetails.getCorrectanswer());
            game.setScoreOffensive(questionDetails.getScoreOffensive());
            game.setScoreDefensive(questionDetails.getScoreDefensive());
            game.setAnswertwo(questionDetails.getAnswertwo());
            game.setAnswerthree(questionDetails.getAnswerthree());
            game.setObjectName(questionDetails.getObjectName());
            gameRepository.save(game);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //DELETE: question
    @DeleteMapping("/question/{number}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable int number){
        Game q = gameRepository.findGameByGameId(number);

        if(q !=null) {
            gameRepository.delete(q);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }


    }



}
