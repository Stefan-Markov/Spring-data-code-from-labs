package course.productshop.service;

import course.productshop.domain.dtos.CategorySeedDto;
import course.productshop.domain.dtos.view.CategoriesByProductsCountDto;
import course.productshop.domain.entities.Category;
import course.productshop.repositories.CategoryRepository;
import course.productshop.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        for (CategorySeedDto dto : categorySeedDtos) {
            if (!this.validatorUtil.isValid(dto)) {
                this.validatorUtil
                        .violations(dto)
                        .forEach(v -> System.out.println(v.getMessage()));
                continue;
            }
            Category entity = this.modelMapper.map(dto, Category.class);
            this.categoryRepository.saveAndFlush(entity);
        }
    }


    @Override
    public List<CategoriesByProductsCountDto> getCategoriesByProductsCount() {
        return this.categoryRepository.getCategoriesByProductsCount();
    }
}
