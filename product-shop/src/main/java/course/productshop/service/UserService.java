package course.productshop.service;

import course.productshop.domain.dtos.UserSeedDto;
import course.productshop.domain.dtos.view.UsersWithAtLeastOneSoldItemDto;
import course.productshop.domain.dtos.view.WrapperDto;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    List<UsersWithAtLeastOneSoldItemDto> getSellers();

    WrapperDto getAllSellersByCount();
}
