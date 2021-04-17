package course.productshop.service;

import course.productshop.domain.dtos.CategorySeedDto;
import course.productshop.domain.dtos.view.CategoriesByProductsCountDto;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<CategoriesByProductsCountDto> getCategoriesByProductsCount();
}
