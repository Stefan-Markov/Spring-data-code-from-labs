package coure.springdataadvanced.dao;

import coure.springdataadvanced.entity.Ingredient;
import coure.springdataadvanced.entity.Shampoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByNameIn(Iterable<String> names);


    @Transactional
    int deleteAllByName(String name);

    Optional<Ingredient> findByName(String name);

    @Transactional
    @Modifying
    @Query("update Ingredient  i set i.price = i.price *  :increase_percentage where " +
            " i.name in :ingredient_name")
    int updatePriceOfIngredientsList(@Param("ingredient_name") Iterable<String> ingredientNames,
                                     @Param("increase_percentage") double percentage);
}
