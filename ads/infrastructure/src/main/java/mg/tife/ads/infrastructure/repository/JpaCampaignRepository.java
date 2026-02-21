package mg.tife.ads.infrastructure.repository;

import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.model.campaign.CampaignStatus;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.infrastructure.model.CampaignEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaCampaignRepository implements CampaignRepository {

    private final CampaignEntityRepository delegate;

    public JpaCampaignRepository(CampaignEntityRepository delegate) {
        this.delegate = delegate;
    }

    @Override
    public Campaign save(Campaign campaign) {
        CampaignEntity entity = toEntity(campaign);
        CampaignEntity saved = delegate.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<Campaign> findById(UUID id) {
        return delegate.findById(id).map(this::toDomain);
    }

    @Override
    public List<Campaign> findActiveCampaigns() {
        return delegate.findByActiveTrue()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private CampaignEntity toEntity(Campaign domain) {
        CampaignEntity e = new CampaignEntity();
        if (domain.getId() != null) {
            e.setId(domain.getId());
        }
        e.setName(domain.getName());
        // map status -> active flag (simple mapping)
        e.setActive(CampaignStatus.ACTIVE.equals(domain.getStatus()));
        return e;
    }

    private Campaign toDomain(CampaignEntity entity) {
        // Creating a lightweight domain object. Some domain fields are not persisted in CampaignEntity
        // so we set minimal values. Assumption: advertiserId unknown here and set to null.
        Campaign c = new Campaign(null);
        c.setId(entity.getId());
        c.setName(entity.getName());
        c.setStatus(entity.isActive() ? CampaignStatus.ACTIVE : CampaignStatus.DRAFT);
        return c;
    }
}