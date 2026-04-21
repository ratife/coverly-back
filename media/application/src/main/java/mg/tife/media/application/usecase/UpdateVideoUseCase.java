package mg.tife.media.application.usecase;

import mg.tife.media.domain.Video;
import mg.tife.media.domain.repository.VideoRepository;

import java.util.UUID;

public class UpdateVideoUseCase {
    private final VideoRepository videoRepository;

    public UpdateVideoUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public UUID execute(UUID videoId, Video updatedVideo) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + videoId));
        
        updatedVideo.setId(videoId);
        Video saved = videoRepository.save(updatedVideo);
        return saved.getId();
    }
}

