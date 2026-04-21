package mg.tife.media.domain.repository;

import mg.tife.media.domain.Picture;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PictureRepository {
    Picture save(Picture picture);
    Optional<Picture> findById(UUID id);
    List<Picture> findAll();
    List<Picture> findByCampaignId(UUID campaignId);
    void delete(UUID id);
    void deleteAll();
}

