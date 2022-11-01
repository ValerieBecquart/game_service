package fact.it.game_service.controller;

import fact.it.game_service.model.Game;
import fact.it.game_service.model.GameDTO;
import fact.it.game_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameRestController {

    @Autowired
    private GameRepository gameRepository;

    @PostConstruct
    public void fillDB(){

        if (gameRepository.count() == 0){
            gameRepository.save(new Game(1,"vraag 1", 1, 123.45, 567.89, "juist", "fout", "fout",4,4,"EXTRA_PotionVial"));
            gameRepository.save(new Game(2,"vraag 2", 1, 150, 50, "juist", "fout", "fout",5,0,"DEF_RoundShield"));
            gameRepository.save(new Game(3,"vraag 3",1 , 124.45, 568.89, "juist", "fout", "fout",0,10,"OFF_Crossbow"));
            gameRepository.save(new Game(4,"vraag 4",2, 122.45, 566.89, "juist", "fout", "fout",0,2,"OFF_Pickaxe"));
        }

       System.out.println(gameRepository.findGameByGameId(1).getQuestion());
    }

    //get all questions
    @GetMapping("/questions")
    public List<Game> getAllQuestions(){

        return gameRepository.findAll();
    }

    //Get question by object name
    //GET: question?objectname={objectname}
    @GetMapping("/question/{objectname}")
    public Game getQuestionByObjectName(@PathVariable String objectname){
        Game g = gameRepository.findGameByObjectName(objectname);
        return g;
    }
    @GetMapping("/questionsbylevel/{level}")
    public List<Game> getAllQuestionsByLevel(@PathVariable int level){
        List<Game> q = gameRepository.findAllByLevelIs(level);
        return q;
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

        return gameRepository.save(peristentQuestion);
    }

    //PUT:
    @PutMapping("/question")
    public Game updateQuestion(@RequestBody Game questionDetails){
        Game q = gameRepository.findGameByGameId(questionDetails.getGameId());
        q.setQuestion(questionDetails.getQuestion());
        q.setLevel(questionDetails.getLevel());
        q.setX(questionDetails.getX());
        q.setY(questionDetails.getY());
        q.setCorrectanswer(questionDetails.getCorrectanswer());
        q.setAnswertwo(questionDetails.getAnswertwo());
        q.setAnswerthree(questionDetails.getAnswerthree());
        q.setObjectName(questionDetails.getObjectName());
        q.setScoreDefensive(questionDetails.getScoreDefensive());
        q.setScoreOffensive(questionDetails.getScoreOffensive());
        return gameRepository.save(q);
    }


    //DELETE: question
    @DeleteMapping("/question/{number}")
    public ResponseEntity deleteQuestion(@PathVariable int number){
        Game q = gameRepository.findGameByGameId(number);
        if(q !=null) {
            gameRepository.delete(q);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }


    }



}
