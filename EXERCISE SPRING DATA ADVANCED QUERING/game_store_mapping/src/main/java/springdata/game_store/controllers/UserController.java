package springdata.game_store.controllers;

import springdata.game_store.AutenticationContext;
import springdata.game_store.domain.entities.User;
import springdata.game_store.domain.services.api.UserService;

public class UserController {
    private final UserService userService;
    private final AutenticationContext autenticationContext;

    public UserController(UserService userService, AutenticationContext autenticationContext) {
        this.userService = userService;
        this.autenticationContext = autenticationContext;
    }

    private String register(String email, String password, String confirmPassword, String fullName) {
        User user1 = new User();
        user1.setEmail(email);
        user1.setPassword(password);
        user1.setFullName(fullName);

        try {
            this.userService.registerUser(user1, confirmPassword);
        } catch (IllegalArgumentException iae) {
            return iae.getMessage();
        }

        return fullName + " was registered";
    }

    public String login(String email, String password) {
        if (!this.userService.checkIfUserExists(email)) {
            return "User does not exist!";
        }

        this.autenticationContext.setLoggedUser(this.userService.getUserDtoByEmail(email));
        return "User logged in successfully";
    }

    public String logout() {
        if (this.autenticationContext.getLoggedUser() == null) {
            return "Cannot log out. No user was logged in.";
        }

        this.autenticationContext.setLoggedUser(null);
        return "Successfully logged out!";
    }

}
