package mg.tife.media.application.usecase;

import mg.tife.media.domain.Video;
import mg.tife.media.domain.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetVideoUseCaseTest {

    @Mock
    private VideoRepository videoRepository;

    private GetVideoUseCase getVideoUseCase;

    @BeforeEach
    void setUp() {
        getVideoUseCase = new GetVideoUseCase(videoRepository);
    }

    @Test
    void execute_success_returns_video() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        Video video = new Video();
        video.setId(videoId);
        video.setTitle("Test Video");
        video.setDurationSeconds(120);
        video.setUrl("http://example.com/video.mp4");

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));

        // Act
        Optional<Video> result = getVideoUseCase.execute(videoId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(videoId, result.get().getId());
        assertEquals("Test Video", result.get().getTitle());
        verify(videoRepository).findById(videoId);
    }

    @Test
    void execute_video_not_found_returns_empty() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());

        // Act
        Optional<Video> result = getVideoUseCase.execute(videoId);

        // Assert
        assertFalse(result.isPresent());
        verify(videoRepository).findById(videoId);
    }

    @Test
    void execute_with_valid_id() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        Video video = new Video();
        video.setId(videoId);
        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));

        // Act
        Optional<Video> result = getVideoUseCase.execute(videoId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(videoId, result.get().getId());
    }

    @Test
    void execute_multiple_calls() {
        // Arrange
        UUID videoId1 = UUID.randomUUID();
        UUID videoId2 = UUID.randomUUID();
        
        Video video1 = new Video();
        video1.setId(videoId1);
        
        Video video2 = new Video();
        video2.setId(videoId2);

        when(videoRepository.findById(videoId1)).thenReturn(Optional.of(video1));
        when(videoRepository.findById(videoId2)).thenReturn(Optional.of(video2));

        // Act
        Optional<Video> result1 = getVideoUseCase.execute(videoId1);
        Optional<Video> result2 = getVideoUseCase.execute(videoId2);

        // Assert
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertEquals(videoId1, result1.get().getId());
        assertEquals(videoId2, result2.get().getId());
        verify(videoRepository, times(2)).findById(any(UUID.class));
    }
}

