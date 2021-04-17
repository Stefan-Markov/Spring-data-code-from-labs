package springdata.game_store;

import springdata.game_store.domain.dtos.UserDto;

public class AutenticationContext {
    private UserDto loggedUser;

    public UserDto getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserDto loggedUser) {
        this.loggedUser = loggedUser;
    }
}
