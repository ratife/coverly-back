package mg.tife.media.application.usecase;

import mg.tife.media.domain.repository.VideoRepository;

import java.util.UUID;

public class DeleteVideoUseCase {
    private final VideoRepository videoRepository;

    public DeleteVideoUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void execute(UUID videoId) {
        videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + videoId));
        videoRepository.delete(videoId);
    }
}

