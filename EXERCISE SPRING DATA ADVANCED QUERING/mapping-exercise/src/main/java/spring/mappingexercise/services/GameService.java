package spring.mappingexercise.services;

import spring.mappingexercise.dto.AddGameDto;
import spring.mappingexercise.dto.DeleteGameDto;
import spring.mappingexercise.dto.UserDto;
import spring.mappingexercise.entities.Game;

public interface GameService {
    String addGame(AddGameDto game);

    void setLoggedUser(UserDto userDto);

    String deleteGame(DeleteGameDto deleteGameDto);
}
