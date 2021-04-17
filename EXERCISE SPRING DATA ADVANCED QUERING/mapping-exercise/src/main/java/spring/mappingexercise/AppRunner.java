package spring.mappingexercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.mappingexercise.dto.AddGameDto;
import spring.mappingexercise.dto.DeleteGameDto;
import spring.mappingexercise.dto.UserLoginDto;
import spring.mappingexercise.dto.UserRegisterDto;
import spring.mappingexercise.services.GameService;
import spring.mappingexercise.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class AppRunner implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;


    @Autowired
    public AppRunner(UserService userService, GameService gameService) {
        this.userService = userService;

        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split("\\|");

        while (true) {
            switch (tokens[0]) {

                case "RegisterUser":
                    UserRegisterDto user = new UserRegisterDto(tokens[1], tokens[2],
                            tokens[3], tokens[4]);
                    System.out.println(this.userService.registerUser(user));
                    break;
                case "LoginUser":
                    UserLoginDto userLogin = new UserLoginDto(tokens[1], tokens[2]);

                    this.userService.loginUser(userLogin);
                    System.out.println(this.userService.loginUser(userLogin));
                    break;
                case "Logout":
                    System.out.println(this.userService.logout());
                    break;
                case "AddGame":
                    LocalDate date = LocalDate.parse(tokens[6],
                            DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    AddGameDto addGameDto = new AddGameDto(tokens[1],
                            new BigDecimal(tokens[2]), Double.parseDouble(tokens[3]),
                            tokens[4], tokens[5], date);
                case "DeleteGame":
                    DeleteGameDto deleteGameDto = new DeleteGameDto(Long.parseLong(tokens[1]));
                    System.out.println(this.gameService.deleteGame(deleteGameDto));
                    break;
            }
        }
    }
}
