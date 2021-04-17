package spring.jsonlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.jsonlab.entities.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String name);
}
