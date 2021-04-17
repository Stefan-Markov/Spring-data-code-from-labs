package course.productshop.controller;

import com.google.gson.Gson;
import course.productshop.constants.FileConstants;
import course.productshop.domain.dtos.CategorySeedDto;
import course.productshop.domain.dtos.ProductSeedDto;
import course.productshop.domain.dtos.UserSeedDto;
import course.productshop.domain.dtos.view.CategoriesByProductsCountDto;
import course.productshop.domain.dtos.view.ProductsInRangeDto;
import course.productshop.domain.dtos.view.UsersWithAtLeastOneSoldItemDto;
import course.productshop.domain.dtos.view.WrapperDto;
import course.productshop.service.CategoryService;
import course.productshop.service.ProductService;
import course.productshop.service.UserService;
import course.productshop.utils.FileIOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@org.springframework.stereotype.Controller
@Transactional
public class Controller  implements CommandLineRunner {

    private UserService userService;
    private CategoryService categoryService;
    private ProductService productService;
    private FileIOUtil fileIOUtil;
    private  Gson gson;
    private FileWriter writer;

    @Autowired
    public Controller(UserService userService, CategoryService categoryService, ProductService productService, FileIOUtil fileIOUtil, Gson gson) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileIOUtil = fileIOUtil;
        this.gson = gson;

    }




    @Override
    public void run(String... args) throws Exception {
         this.seedUsers();
//         this.seedCategories();
//         this.seedProducts();
        // this.successfullySoldProducts();
        //  this.categoriesByProductCount();
//        this.usersAndProducts();
    }
    private void seedUsers() throws IOException {
        String userFileContent = this.fileIOUtil.readFile(FileConstants.USERS_FILE_PATH);

        UserSeedDto[] userSeedDtos = this.gson.fromJson(userFileContent, UserSeedDto[].class);
        this.userService.seedUsers(userSeedDtos);
    }

    private void seedCategories() throws IOException {
        String userFileContent = this.fileIOUtil.readFile(FileConstants.CATEGORIES_FILE_PATH);

        CategorySeedDto[] categorySeedDtos = this.gson.fromJson(userFileContent, CategorySeedDto[].class);
        this.categoryService.seedCategories(categorySeedDtos);
    }

    private void seedProducts() throws IOException {
        String userFileContent = this.fileIOUtil.readFile(FileConstants.PRODUCTS_FILE_PATH);

        ProductSeedDto[] productSeedDtos = this.gson.fromJson(userFileContent, ProductSeedDto[].class);
        this.productService.seedProducts(productSeedDtos);
    }

    /**
     * Query 1 - Products In Range
     */
    private void productsInRange() throws IOException {
        List<ProductsInRangeDto> productsInRangeDtos = this.productService.
                productsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));

        String productsInRangeJson = this.gson.toJson(productsInRangeDtos);

        writeOnFile(FileConstants.PRODUCTS_IN_RANGE_FILE_PATH, productsInRangeJson);
    }

    /**
     * Query 2 - Successfully Sold Products
     */
    private void successfullySoldProducts() throws IOException {

        List<UsersWithAtLeastOneSoldItemDto> users = this.userService.getSellers();
        String successfullySoldProductsJson = this.gson.toJson(users);

        writeOnFile(FileConstants.USER_SOLD_PRODUCTS_FILE_PATH, successfullySoldProductsJson);
    }

    /**
     * Query 3 - Categories By Products Count
     */
    private void categoriesByProductCount() throws IOException {
        List<CategoriesByProductsCountDto> categoriesByProductsCountDtos = this.categoryService.getCategoriesByProductsCount();
        String categoriesByProductsCountJson = this.gson.toJson(categoriesByProductsCountDtos);

        writeOnFile(FileConstants.CATEGORIES_BY_PRODUCTS_FILE_PATH, categoriesByProductsCountJson);
    }

    private void writeOnFile(String writeWhere, String writeWhat) throws IOException {

        File file = new File(writeWhere);
        this.writer = new FileWriter(file);
        writer.write(writeWhat);
        writer.close();
    }

    /**
     * Query 4 - Users and Products
     */
    private void usersAndProducts() throws IOException {
        WrapperDto wrapper = this.userService.getAllSellersByCount();
        String usersAndProductsJson = this.gson.toJson(wrapper);

        writeOnFile(FileConstants.USERS_AND_PRODUCTS_FILE_PATH, usersAndProductsJson);
    }

}
