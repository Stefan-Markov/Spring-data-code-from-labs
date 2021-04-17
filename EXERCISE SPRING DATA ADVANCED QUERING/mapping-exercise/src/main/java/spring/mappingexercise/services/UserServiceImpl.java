package spring.mappingexercise.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mappingexercise.dto.UserDto;
import spring.mappingexercise.dto.UserLoginDto;
import spring.mappingexercise.dto.UserRegisterDto;
import spring.mappingexercise.entities.Role;
import spring.mappingexercise.entities.User;
import spring.mappingexercise.repositories.UserRepository;
import spring.mappingexercise.utils.ValidatorUtil;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final GameService gameService;
    private UserDto loggedUser;


    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ValidatorUtil validatorUtil, GameService gameService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.gameService = gameService;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            sb.append("Password don't match");
        } else if (this.validatorUtil.isValid(userRegisterDto)) {
            User user = this.modelMapper.map(userRegisterDto, User.class);
            if (this.userRepository.count() == 0) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }
            sb.append(String.format("%s was registered", userRegisterDto.getFullName()));
            this.userRepository.saveAndFlush(user);
        } else {
            this.validatorUtil.violations(userRegisterDto).forEach(e -> sb
                    .append(String.format("%s %n", e.getMessage())));
        }

        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        Optional<User> user = this.userRepository
                .findAllByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if (user.isPresent()) {
            if (this.loggedUser != null) {
                sb.append("User is already logged in.");
            } else {
                this.loggedUser = this.modelMapper.map(user.get(), UserDto.class);
                this.gameService.setLoggedUser(this.loggedUser);
                sb.append(String.format(
                        "Successfully logged in: %s", user.get().getFullName()));
            }
        } else {
            sb.append("Incorrect email/password");
        }
        return sb.toString();
    }

    @Override
    public String logout() {
        if (this.loggedUser == null) {
            return "Cannot log out. No user was logged in.";
        } else {
            this.loggedUser = null;
            return "User logout successfully.";
        }
    }
}
