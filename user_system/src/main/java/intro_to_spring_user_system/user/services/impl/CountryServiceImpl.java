package intro_to_spring_user_system.user.services.impl;

import intro_to_spring_user_system.user.repositories.CountryRepository;
import intro_to_spring_user_system.user.services.api.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CountryServiceImpl   implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(final CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
}
