package springdata.game_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.game_store.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
//    @Query("SELECT new gamestore.domain.dtos.UserDto(u.email, u.isAdmin) FROM User AS u WHERE u.email = :target ")
//    User getUserByEmail(@Param(value = "target") String email);
}
