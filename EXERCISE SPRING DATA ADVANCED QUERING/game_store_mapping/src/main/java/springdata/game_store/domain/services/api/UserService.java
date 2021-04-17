package springdata.game_store.domain.services.api;

import springdata.game_store.domain.dtos.UserDto;
import springdata.game_store.domain.entities.User;

public interface UserService {
    void registerUser(User user, String confirmPassword);

    boolean checkIfUserExists(String email);

    UserDto getUserDtoByEmail(String email);

}
