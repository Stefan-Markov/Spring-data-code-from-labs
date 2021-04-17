package spring.mappingexercise.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mappingexercise.dto.AddGameDto;
import spring.mappingexercise.dto.DeleteGameDto;
import spring.mappingexercise.dto.UserDto;
import spring.mappingexercise.entities.Game;
import spring.mappingexercise.entities.Role;
import spring.mappingexercise.repositories.GameRepository;
import spring.mappingexercise.utils.ValidatorUtil;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final ValidatorUtil validatorUtil;
    private UserDto userDto;


    @Autowired
    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, ValidatorUtil validatorUtil) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String addGame(AddGameDto gameDto) {
        StringBuilder sb = new StringBuilder();
        if (this.userDto == null || this.userDto.getRole().equals(Role.USER)) {
            sb.append("Invalid logged user");
        } else if (!this.validatorUtil.isValid(gameDto)) {
            Game game = this.modelMapper.map(gameDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            sb.append("Added successfully game!");
        } else {
            this.validatorUtil.violations(gameDto)
                    .forEach(e -> sb.append(e.getMessage()).append("\n"));
        }
        return sb.toString();
    }

    @Override
    public void setLoggedUser(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String deleteGame(DeleteGameDto deleteGameDto) {
        StringBuilder sb = new StringBuilder();

        if (this.userDto == null || this.userDto.getRole().equals(Role.USER)) {
            sb.append("Invalid logged user");
        } else {
            Optional<Game> game = this.gameRepository.findById(deleteGameDto.getId());
            if (game.isPresent()) {
                this.gameRepository.delete(game.get());
                sb.append("Successfully deleted game");
            } else {
                sb.append("Cannot find game.");
            }
        }

        return sb.toString();
    }
}
