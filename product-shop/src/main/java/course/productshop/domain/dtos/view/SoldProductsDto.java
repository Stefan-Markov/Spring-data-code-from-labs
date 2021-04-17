package course.productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class SoldProductsDto {
    @Expose
    private Integer count;
    @Expose
    private List<SimpleProductsDto> products;

    public SoldProductsDto() {
        this.products=new ArrayList<>();
    }

    public SoldProductsDto(Integer count, List<SimpleProductsDto> products) {
        this.count = count;
        this.products = products;
    }
}
