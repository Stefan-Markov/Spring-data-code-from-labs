package intro_to_spring_user_system.user.services.impl;

import intro_to_spring_user_system.user.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PictureServiceImpl {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(final PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

}
