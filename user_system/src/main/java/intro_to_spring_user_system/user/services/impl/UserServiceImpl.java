package intro_to_spring_user_system.user.services.impl;

import intro_to_spring_user_system.user.entities.User;
import intro_to_spring_user_system.user.repositories.UserRepository;
import intro_to_spring_user_system.user.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsersByEmailProvider(final String provider) {
        return this.userRepository.findAllByEmailEndingWith(provider);
    }


    @Override
    public void deactivateInactiveUsers(Date date) {
        this.userRepository
                .findAllByLastTimeLoggedInBefore(date)
                .forEach(user -> user.setDeleted(true));
    }

    @Override
    public void save(final User user) {
        this.userRepository.save(user);
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public List<String> getUserNamesAndAgeByAgeRange(final int lowBound, final int highBound) {
        return this.userRepository.findAllByAgeBetween(lowBound, highBound)
                .stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .map(user -> String.format("%s %s - %d years old",
                        user.getFirstName(), user.getLastName(), user.getAge()))
                .collect(Collectors.toList());
    }
}
