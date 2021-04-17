package spring.jsonlab.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.jsonlab.dao.UserRepository;
import spring.jsonlab.entities.Post;
import spring.jsonlab.entities.User;
import spring.jsonlab.exception.NonexistingEntityException;
import spring.jsonlab.service.UserService;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() ->
                new NonexistingEntityException(String.format("Post with ID=%s", id)));

    }

    @Override
    public User addUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        getUserById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(Long id) {
        User removed = getUserById(id);
        userRepository.deleteById(id);
        return removed;
    }

    @Override
    public long getUsersCount() {
        return this.userRepository.count();
    }
}
