package mg.tife.ads.infrastructure.repository;

import mg.tife.ads.infrastructure.entity.MissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MissionEntityRepository extends JpaRepository<MissionEntity, UUID> {
    List<MissionEntity> findByCampaignId(UUID campaignId);
}

