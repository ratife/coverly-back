package mg.tife.media.application.usecase;

import mg.tife.media.domain.Video;
import mg.tife.media.domain.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateVideoUseCaseTest {

    @Mock
    private VideoRepository videoRepository;

    private CreateVideoUseCase createVideoUseCase;

    @BeforeEach
    void setUp() {
        createVideoUseCase = new CreateVideoUseCase(videoRepository);
    }

    @Test
    void execute_success() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        Video video = new Video();
        video.setTitle("Test Video");
        video.setDurationSeconds(120);
        video.setUrl("http://example.com/video.mp4");
        video.setCampaignId(UUID.randomUUID());

        Video savedVideo = new Video();
        savedVideo.setId(videoId);
        savedVideo.setTitle("Test Video");
        savedVideo.setDurationSeconds(120);
        savedVideo.setUrl("http://example.com/video.mp4");
        savedVideo.setCampaignId(video.getCampaignId());

        when(videoRepository.save(video)).thenReturn(savedVideo);

        // Act
        UUID result = createVideoUseCase.execute(video);

        // Assert
        assertEquals(videoId, result);
        verify(videoRepository).save(video);
    }

    @Test
    void execute_returns_correct_id() {
        // Arrange
        UUID expectedId = UUID.randomUUID();
        Video video = new Video();
        video.setTitle("Another Video");

        Video savedVideo = new Video();
        savedVideo.setId(expectedId);
        savedVideo.setTitle("Another Video");

        when(videoRepository.save(video)).thenReturn(savedVideo);

        // Act
        UUID result = createVideoUseCase.execute(video);

        // Assert
        assertNotNull(result);
        assertEquals(expectedId, result);
    }

    @Test
    void execute_with_null_repository_throws_exception() {
        // Arrange
        CreateVideoUseCase useCase = new CreateVideoUseCase(null);
        Video video = new Video();

        // Act & Assert
        assertThrows(NullPointerException.class, () -> useCase.execute(video));
    }
}

