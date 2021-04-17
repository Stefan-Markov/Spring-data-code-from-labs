package spring.mappingexercise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mappingexercise.entities.Game;

public interface GameRepository extends JpaRepository<Game,Long> {
}
