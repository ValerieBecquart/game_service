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
            gameRepository.save(new Game(1,"vraag 1", "theme 1", 123.45, 567.89, "juist", "fout", "fout"));
            gameRepository.save(new Game(2,"vraag 2", "theme 2", 124.45, 568.89, "juist", "fout", "fout"));
            gameRepository.save(new Game(3,"vraag 3", "theme 2", 122.45, 566.89, "juist", "fout", "fout"));
        }

        System.out.println(gameRepository.findGameByGameId(1).getQuestion());
    }

    //get all questions
    @RequestMapping("question")
    public List<Game> getAllQuestions(){

        return gameRepository.findAll();
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
        q.setTheme(questionDetails.getTheme());
        q.setX(questionDetails.getX());
        q.setY(questionDetails.getY());
        q.setCorrectanswer(questionDetails.getCorrectanswer());
        q.setAnswertwo(questionDetails.getAnswertwo());
        q.setAnswerthree(questionDetails.getAnswerthree());
        return gameRepository.save(q);
    }


    //DELETE: question
    @DeleteMapping("/question/{number}")
    public void deleteQuestion(@PathVariable int number){
        Game q = gameRepository.findGameByGameId(number);
        gameRepository.delete(q);
    }



}
