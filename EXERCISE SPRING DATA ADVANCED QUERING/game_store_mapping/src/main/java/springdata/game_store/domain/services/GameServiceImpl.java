package springdata.game_store.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springdata.game_store.domain.entities.Game;
import springdata.game_store.domain.services.api.GameService;
import springdata.game_store.repository.GameRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private Validator validator;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.getValidator();
    }

    private void getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void addGame(Game game) {
        if (this.checkIfGameExist(game.getTitle())) {
            throw new IllegalArgumentException("Game already exists!");
        }
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<Game>> violations = validator.validate(game);

        if (violations.size() == 0) {
            this.gameRepository.save(game);
        } else {
            violations.forEach(err ->
                    sb.append(err.getMessage())
                            .append(System.lineSeparator()));
            System.out.println(sb.toString().trim());
        }
    }

    public boolean checkIfGameExist(String title) {
        Game game = this.gameRepository.findByTitle(title);
        return game != null;
    }
}
