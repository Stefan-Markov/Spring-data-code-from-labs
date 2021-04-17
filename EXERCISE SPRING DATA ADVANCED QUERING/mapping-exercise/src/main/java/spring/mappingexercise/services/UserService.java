package spring.mappingexercise.services;

import spring.mappingexercise.dto.UserLoginDto;
import spring.mappingexercise.dto.UserRegisterDto;

public interface UserService {
    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logout();
}
