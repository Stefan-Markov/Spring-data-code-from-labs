package springdata.game_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springdata.game_store.domain.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {

    Game findByTitle(String title);
}
