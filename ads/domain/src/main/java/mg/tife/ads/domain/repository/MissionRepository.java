package mg.tife.ads.domain.repository;

import mg.tife.ads.domain.model.mission.Mission;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MissionRepository {

    Mission save(Mission mission);

    Optional<Mission> findById(UUID id);

    List<Mission> findByCampaignId(UUID campaignId);
}