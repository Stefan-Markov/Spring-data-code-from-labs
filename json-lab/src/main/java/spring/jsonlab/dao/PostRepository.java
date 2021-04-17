package spring.jsonlab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.jsonlab.entities.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post,Long> {
}
