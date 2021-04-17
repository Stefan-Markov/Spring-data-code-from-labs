package intro_to_spring_user_system.user.services.api;

import intro_to_spring_user_system.user.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> getAllUsersByEmailProvider(String provider);

    void deactivateInactiveUsers(Date date);

    void save(User user);

    long getUsersCount();

    List<String> getUserNamesAndAgeByAgeRange(int lowBound, int highBound);

}
