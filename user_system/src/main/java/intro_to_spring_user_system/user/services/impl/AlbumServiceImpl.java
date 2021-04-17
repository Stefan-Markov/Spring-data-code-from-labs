package intro_to_spring_user_system.user.services.impl;

import intro_to_spring_user_system.user.repositories.AlbumRepository;
import intro_to_spring_user_system.user.services.api.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(final AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }
}
