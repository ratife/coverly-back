package mg.tife.ads.infrastructure.repository;

import mg.tife.ads.domain.model.campaign.Campaign;
import mg.tife.ads.domain.repository.CampaignRepository;
import mg.tife.ads.infrastructure.mapper.CampaignMapper;
import mg.tife.ads.infrastructure.entity.CampaignEntity;
import org.springframework.data.domain.Pageable;
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
        System.out.println("Saving campaign: " + campaign);
        CampaignEntity entity = CampaignMapper.INSTANCE.toEntity(campaign);
        CampaignEntity saved = delegate.save(entity);
        System.out.println("Saved campaign with ID: " + saved.getId());
        return CampaignMapper.INSTANCE.toDomain(saved);
    }

    @Override
    public Optional<Campaign> findById(UUID id) {
        return delegate.findById(id).map(CampaignMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Campaign> findActiveCampaigns(int page, int size) {
        return delegate.findAll(Pageable.ofSize(size).withPage(page))
                .stream()
                .filter(CampaignEntity::isActive)
                .map(CampaignMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Campaign> find(int page, int size) {
        return delegate.findAll(Pageable.ofSize(size).withPage(page))
                .stream()
                .map(CampaignMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());
    }
}