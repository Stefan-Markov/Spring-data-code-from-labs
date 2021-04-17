package coure.springdataadvanced.dao;

import coure.springdataadvanced.entity.Ingredient;
import coure.springdataadvanced.entity.Label;
import coure.springdataadvanced.entity.Shampoo;
import coure.springdataadvanced.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    @Query("select  s from Shampoo  s, In(s.ingredients) i where i = :ingredient")
    List<Shampoo> findByIngredient(Ingredient ingredient);

    List<Shampoo> findBySizeOrderById(Size size);

    @Query("select s from Shampoo s join s.ingredients i where  i.name in :ingredients")
    List<Shampoo> findWithIngredientsIn(@Param("ingredients") Iterable<String> ingredients);

    @Query("select s from Shampoo as s  where s.ingredients.size < :count ")
    List<Shampoo> countByIngredients(@Param("count") int count);

    List<Shampoo> findBySizeOrLabelOrderByPriceDesc(Size medium, Label s);

    List<Shampoo> findByPriceGreaterThan(double minPrice);

    List<Shampoo> findByPriceBetween(double min, double max);

    int countShampoosByPriceLessThan(double price);
}
