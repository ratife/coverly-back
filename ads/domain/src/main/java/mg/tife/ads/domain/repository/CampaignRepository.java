package mg.tife.ads.domain.repository;

import mg.tife.ads.domain.model.campaign.Campaign;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CampaignRepository {

    Campaign save(Campaign campaign);

    Optional<Campaign> findById(UUID id);

    List<Campaign> findActiveCampaigns();
}