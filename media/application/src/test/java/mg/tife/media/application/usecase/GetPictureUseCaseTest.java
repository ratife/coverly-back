package mg.tife.media.application.usecase;

import mg.tife.media.domain.Picture;
import mg.tife.media.domain.repository.PictureRepository;
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
class GetPictureUseCaseTest {

    @Mock
    private PictureRepository pictureRepository;

    private GetPictureUseCase getPictureUseCase;

    @BeforeEach
    void setUp() {
        getPictureUseCase = new GetPictureUseCase(pictureRepository);
    }

    @Test
    void execute_success_returns_picture() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        Picture picture = new Picture();
        picture.setId(pictureId);
        picture.setTitle("Test Picture");
        picture.setDurationSeconds(0);
        picture.setUrl("http://example.com/picture.jpg");

        when(pictureRepository.findById(pictureId)).thenReturn(Optional.of(picture));

        // Act
        Optional<Picture> result = getPictureUseCase.execute(pictureId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(pictureId, result.get().getId());
        assertEquals("Test Picture", result.get().getTitle());
        verify(pictureRepository).findById(pictureId);
    }

    @Test
    void execute_picture_not_found_returns_empty() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        when(pictureRepository.findById(pictureId)).thenReturn(Optional.empty());

        // Act
        Optional<Picture> result = getPictureUseCase.execute(pictureId);

        // Assert
        assertFalse(result.isPresent());
        verify(pictureRepository).findById(pictureId);
    }

    @Test
    void execute_with_valid_id() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        Picture picture = new Picture();
        picture.setId(pictureId);
        when(pictureRepository.findById(pictureId)).thenReturn(Optional.of(picture));

        // Act
        Optional<Picture> result = getPictureUseCase.execute(pictureId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(pictureId, result.get().getId());
    }

    @Test
    void execute_multiple_calls() {
        // Arrange
        UUID pictureId1 = UUID.randomUUID();
        UUID pictureId2 = UUID.randomUUID();
        
        Picture picture1 = new Picture();
        picture1.setId(pictureId1);
        
        Picture picture2 = new Picture();
        picture2.setId(pictureId2);

        when(pictureRepository.findById(pictureId1)).thenReturn(Optional.of(picture1));
        when(pictureRepository.findById(pictureId2)).thenReturn(Optional.of(picture2));

        // Act
        Optional<Picture> result1 = getPictureUseCase.execute(pictureId1);
        Optional<Picture> result2 = getPictureUseCase.execute(pictureId2);

        // Assert
        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertEquals(pictureId1, result1.get().getId());
        assertEquals(pictureId2, result2.get().getId());
        verify(pictureRepository, times(2)).findById(any(UUID.class));
    }
}

