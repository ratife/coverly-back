package mg.tife.ads.infrastructure.event;


import mg.tife.ads.domain.model.campaign.Campaign;

public record CampaignActivatedEvent(Campaign campaign) {}