package fact.it.game_service.repository;

import fact.it.game_service.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    //Find all questions and order by theme.

    //Find questions by level
    List<Game> findAllByLevelIs(int level);
    //get question by id
    Game findGameByGameId(int gameId);
    //Get question by modelname
 Game findGameByObjectName(String objectName);

}
