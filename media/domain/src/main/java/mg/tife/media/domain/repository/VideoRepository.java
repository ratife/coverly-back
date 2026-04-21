package mg.tife.media.domain.repository;

import mg.tife.media.domain.Video;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoRepository {
    Video save(Video video);
    Optional<Video> findById(UUID id);
    List<Video> findAll();
    List<Video> findByCampaignId(UUID campaignId);
    void delete(UUID id);
    void deleteAll();
}

