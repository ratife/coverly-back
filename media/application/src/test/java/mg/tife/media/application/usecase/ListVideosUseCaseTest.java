package mg.tife.media.application.usecase;

import mg.tife.media.domain.Video;
import mg.tife.media.domain.repository.VideoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListVideosUseCaseTest {

    @Mock
    private VideoRepository videoRepository;

    private ListVideosUseCase listVideosUseCase;

    @BeforeEach
    void setUp() {
        listVideosUseCase = new ListVideosUseCase(videoRepository);
    }

    @Test
    void execute_success_returns_all_videos() {
        // Arrange
        Video video1 = new Video();
        video1.setTitle("Video 1");

        Video video2 = new Video();
        video2.setTitle("Video 2");

        List<Video> videos = Arrays.asList(video1, video2);
        when(videoRepository.findAll()).thenReturn(videos);

        // Act
        List<Video> result = listVideosUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Video 1", result.get(0).getTitle());
        assertEquals("Video 2", result.get(1).getTitle());
        verify(videoRepository).findAll();
    }

    @Test
    void execute_empty_list() {
        // Arrange
        when(videoRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<Video> result = listVideosUseCase.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(videoRepository).findAll();
    }

    @Test
    void execute_single_video() {
        // Arrange
        Video video = new Video();
        video.setTitle("Single Video");

        when(videoRepository.findAll()).thenReturn(Arrays.asList(video));

        // Act
        List<Video> result = listVideosUseCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Single Video", result.get(0).getTitle());
    }

    @Test
    void execute_multiple_videos() {
        // Arrange
        List<Video> videos = new java.util.ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Video video = new Video();
            video.setTitle("Video " + i);
            videos.add(video);
        }

        when(videoRepository.findAll()).thenReturn(videos);

        // Act
        List<Video> result = listVideosUseCase.execute();

        // Assert
        assertEquals(5, result.size());
        verify(videoRepository).findAll();
    }
}

