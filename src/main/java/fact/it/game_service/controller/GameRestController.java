package fact.it.game_service.controller;

import fact.it.game_service.model.Game;
import fact.it.game_service.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("game")
    public List<Game> getAllQuestions(){
        return gameRepository.findAll();
    }

}
