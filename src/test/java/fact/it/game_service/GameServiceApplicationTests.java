package fact.it.game_service;

import fact.it.game_service.controller.GameRestController;
import fact.it.game_service.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class GameServiceApplicationTests {

    @Autowired
    private GameRestController gameController;

    @Autowired
    private GameRepository gameRepository;

    @Test
    void contextLoads() throws Exception {
        assertThat(gameController).isNotNull();
        assertThat(gameRepository).isNotNull();
    }

}


