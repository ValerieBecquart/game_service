package fact.it.game_service.repository;

import fact.it.game_service.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    //Find all questions and order by theme.
    List<Game> findAllByOrderByTheme();
    //Find questions by theme
    List<Game> findAllByThemeIs(String searchString);
    //get question by id
    Game findGameByGameId(int id);

}
