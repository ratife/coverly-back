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
class DeleteVideoUseCaseTest {

    @Mock
    private VideoRepository videoRepository;

    private DeleteVideoUseCase deleteVideoUseCase;

    @BeforeEach
    void setUp() {
        deleteVideoUseCase = new DeleteVideoUseCase(videoRepository);
    }

    @Test
    void execute_success() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        Video video = new Video();
        video.setId(videoId);

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));

        // Act
        deleteVideoUseCase.execute(videoId);

        // Assert
        verify(videoRepository).findById(videoId);
        verify(videoRepository).delete(videoId);
    }

    @Test
    void execute_video_not_found_throws_exception() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> deleteVideoUseCase.execute(videoId));
        verify(videoRepository).findById(videoId);
        verify(videoRepository, never()).delete(videoId);
    }

    @Test
    void execute_with_valid_id() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        Video video = new Video();
        video.setId(videoId);

        when(videoRepository.findById(videoId)).thenReturn(Optional.of(video));

        // Act
        assertDoesNotThrow(() -> deleteVideoUseCase.execute(videoId));

        // Assert
        verify(videoRepository).findById(videoId);
        verify(videoRepository).delete(videoId);
    }

    @Test
    void execute_multiple_deletes() {
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
        deleteVideoUseCase.execute(videoId1);
        deleteVideoUseCase.execute(videoId2);

        // Assert
        verify(videoRepository).delete(videoId1);
        verify(videoRepository).delete(videoId2);
    }

    @Test
    void execute_throws_correct_exception_message() {
        // Arrange
        UUID videoId = UUID.randomUUID();
        when(videoRepository.findById(videoId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deleteVideoUseCase.execute(videoId)
        );
        assertTrue(exception.getMessage().contains("Video not found"));
    }
}

