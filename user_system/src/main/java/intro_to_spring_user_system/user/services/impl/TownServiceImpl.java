package intro_to_spring_user_system.user.services.impl;

import intro_to_spring_user_system.user.repositories.TownRepository;
import intro_to_spring_user_system.user.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(final TownRepository townRepository) {
        this.townRepository = townRepository;
    }

}
