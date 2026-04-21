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
class UpdateVideoUseCaseTest {

    @Mock
    private VideoRepository videoRepository;

    private UpdateVideoUseCase updateVideoUseCase;

    @BeforeEach
    void setUp() {
        updateVideoUseCase = new UpdateVideoUseCase(videoRepository);
    }

    @Test
    void execute_success() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        
        Video existingVideo = new Video();
        existingVideo.setId(videoId);
        existingVideo.setTitle("Old Title");
        existingVideo.setDurationSeconds(100);

        Video updatedVideo = new Video();
        updatedVideo.setTitle("New Title");
        updatedVideo.setDurationSeconds(200);
        updatedVideo.setUrl("http://example.com/new-video.mp4");

        Video savedVideo = new Video();
        savedVideo.setId(videoId);
        savedVideo.setTitle("New Title");
        savedVideo.setDurationSeconds(200);
        savedVideo.setUrl("http://example.com/new-video.mp4");

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepository.save(updatedVideo)).thenReturn(savedVideo);

        // Act
        UUID result = updateVideoUseCase.execute(videoId, updatedVideo);

        // Assert
        assertEquals(videoId, result);
        verify(videoRepository).findById(videoId);
        verify(videoRepository).save(updatedVideo);
    }

    @Test
    void execute_video_not_found_throws_exception() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        Video updatedVideo = new Video();

        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> updateVideoUseCase.execute(videoId, updatedVideo));
        verify(videoRepository).findById(videoId);
        verify(videoRepository, never()).save(updatedVideo);
    }

    @Test
    void execute_updates_all_fields() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        UUID campaignId = UUID.randomUUID();

        Video existingVideo = new Video();
        existingVideo.setId(videoId);

        Video updatedVideo = new Video();
        updatedVideo.setTitle("Updated Title");
        updatedVideo.setDurationSeconds(300);
        updatedVideo.setUrl("http://example.com/updated.mp4");
        updatedVideo.setCampaignId(campaignId);

        Video savedVideo = new Video();
        savedVideo.setId(videoId);
        savedVideo.setTitle("Updated Title");
        savedVideo.setDurationSeconds(300);
        savedVideo.setUrl("http://example.com/updated.mp4");
        savedVideo.setCampaignId(campaignId);

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(existingVideo));
        when(videoRepository.save(updatedVideo)).thenReturn(savedVideo);

        // Act
        UUID result = updateVideoUseCase.execute(videoId, updatedVideo);

        // Assert
        assertEquals(videoId, result);
        assertTrue(updatedVideo.getId().equals(videoId));
    }

    @Test
    void execute_multiple_updates() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        
        Video existingVideo = new Video();
        existingVideo.setId(videoId);

        Video firstUpdate = new Video();
        firstUpdate.setTitle("First Update");

        Video secondUpdate = new Video();
        secondUpdate.setTitle("Second Update");

        when(videoRepository.findById(videoId))
                .thenReturn(Optional.of(existingVideo))
                .thenReturn(Optional.of(existingVideo));

        when(videoRepository.save(firstUpdate)).thenReturn(firstUpdate);
        when(videoRepository.save(secondUpdate)).thenReturn(secondUpdate);

        // Act
        UUID result1 = updateVideoUseCase.execute(videoId, firstUpdate);
        UUID result2 = updateVideoUseCase.execute(videoId, secondUpdate);

        // Assert
        assertEquals(videoId, result1);
        assertEquals(videoId, result2);
        verify(videoRepository, times(2)).findById(videoId);
        verify(videoRepository).save(firstUpdate);
        verify(videoRepository).save(secondUpdate);
    }
}

