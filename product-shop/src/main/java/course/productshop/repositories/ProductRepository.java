package course.productshop.repositories;

import course.productshop.domain.entities.Product;
import course.productshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {
    List<Product> findByPriceBetweenAndBuyerOrderByPrice(BigDecimal more, BigDecimal less, User user);

//    @Query("SELECT u FROM User u JOIN u.id ")
//    List<User> findUsersWithAtLeastOneSell();

}
