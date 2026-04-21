package mg.tife.ads.infrastructure.repository;

import mg.tife.ads.infrastructure.entity.CampaignEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignEntityRepository extends JpaRepository<CampaignEntity, UUID> {
    List<CampaignEntity> findByActiveTrue();
}
