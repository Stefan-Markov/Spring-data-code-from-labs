package coure.springdataadvanced.init;

import coure.springdataadvanced.dao.IngredientRepository;
import coure.springdataadvanced.dao.LabelRepository;
import coure.springdataadvanced.dao.ShampooRepository;
import coure.springdataadvanced.entity.Ingredient;
import coure.springdataadvanced.entity.Shampoo;
import coure.springdataadvanced.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static coure.springdataadvanced.entity.Size.MEDIUM;

@Component
public class AppRunner implements CommandLineRunner {
    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;
    private final IngredientRepository ingredientRepository;


    @Autowired
    public AppRunner(ShampooRepository shampooRepository, LabelRepository labelRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override

    public void run(String... args) throws Exception {

        // fetch Shampoos by Size - ex.1
//        shampooRepository.findBySizeOrderById(MEDIUM)
//                .forEach(PrintUtil::printShampoo);

//        System.out.println("-".repeat(120) + "\n");

        // fetch Shampoos by Size - ex.2
//        shampooRepository.findBySizeOrLabelOrderByPriceDesc(MEDIUM,
//                labelRepository.findByTitle("Vital").get())
//                .forEach(PrintUtil::printShampoo);

//        System.out.println("-".repeat(120) + "\n");

        // fetch Shampoos by Size - ex.3
//        shampooRepository.findByPriceGreaterThan(5)
//                .forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(120) + "\n");


        // demo expanded ex.3
//        shampooRepository.findByPriceBetween(5,8)
//                .forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(120) + "\n");

        // ex.5

//        ingredientRepository.findByNameIn(Set.of("Lavender","Apple"))
//        .forEach(PrintUtil::printIngredients);
//                System.out.println("-".repeat(120) + "\n");


        // ex.6
//        int count = shampooRepository.countShampoosByPriceLessThan(10);
//        System.out.println("Number of shampoos with price less than: " + count);
//        System.out.println("-".repeat(120) + "\n");

        //ex.8
//        shampooRepository.countByIngredients(2).forEach(PrintUtil::printShampoo);
//        System.out.println("-".repeat(120) + "\n");

        //ex9
//        String nameToDelete = "Macadamia Oil";
//        Ingredient ingredientToDelete = ingredientRepository.findByName(nameToDelete).get();
//        List<Shampoo> shampoos = shampooRepository.findByIngredient(ingredientToDelete);
//
//        shampoos.forEach(PrintUtil::printShampoo);
//
//        shampoos.forEach(s -> s.getIngredients().remove(ingredientToDelete));
//
//        int deletedIngr = ingredientRepository.deleteAllByName(nameToDelete);
//        System.out.println("Number of deleted ingredients: " + deletedIngr);
//        System.out.println("-".repeat(120) + "\n");


        //ex. 10 and ex.11

        ingredientRepository.findAll().forEach(PrintUtil::printIngredients);

        System.out.printf("Updated ingredients: %d%n",

                ingredientRepository.updatePriceOfIngredientsList(
                        Set.of("Lavender", "Apple", "Herbs"), 1.1));

        ingredientRepository.findAll().forEach(PrintUtil::printIngredients);
        System.out.println("-".repeat(120) + "\n");


        //fetch Shampoos by ingredients in list
//        shampooRepository.findWithIngredientsIn(Set.of("Berry","Mineral-Collagen")).forEach(PrintUtil::printShampoo);


    }
}
