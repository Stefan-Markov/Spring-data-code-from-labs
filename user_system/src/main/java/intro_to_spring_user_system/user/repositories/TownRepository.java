package intro_to_spring_user_system.user.repositories;

import intro_to_spring_user_system.user.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository  extends JpaRepository<Town,Long> {
}
