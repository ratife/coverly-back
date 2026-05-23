package mg.tife.ads.domain.event.mission;

import mg.tife.ads.domain.model.mission.Mission;

public class MissionEngagedEvent extends MissionEvent {
    public MissionEngagedEvent(Mission mission) {
        super(mission);
    }
}
