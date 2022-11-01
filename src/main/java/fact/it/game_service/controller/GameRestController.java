package fact.it.game_service.controller;

import fact.it.game_service.model.Game;
import fact.it.game_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //POST: question
    @PostMapping("/question")
    public Game createQuestion(@RequestBody Game question){
        return gameRepository.save(question);
    }

    //PUT:
    @PutMapping("/question/{number}")
    public Game updateQuestion(@RequestBody Game questionDetails, @PathVariable int number){
        Game q = gameRepository.findGameByGameId(number);
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
    public void deleteQuestion(@PathVariable int number){
        Game q = gameRepository.findGameByGameId(number);
        gameRepository.delete(q);
    }



}
