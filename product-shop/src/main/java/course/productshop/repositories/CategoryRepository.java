package course.productshop.repositories;

import course.productshop.domain.dtos.view.CategoriesByProductsCountDto;
import course.productshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT new softuni.productshop.domain.dtos.view.CategoriesByProductsCountDto" +
            "(c.name,SIZE(c.products), AVG(p.price), SUM(p.price))" +
            "FROM Category AS c JOIN c.products AS p GROUP BY c.id ORDER BY SIZE(c.products) DESC")
    List<CategoriesByProductsCountDto> getCategoriesByProductsCount();
}
