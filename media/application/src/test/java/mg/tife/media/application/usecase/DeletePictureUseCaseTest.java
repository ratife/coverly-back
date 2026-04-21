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
class DeletePictureUseCaseTest {

    @Mock
    private PictureRepository pictureRepository;

    private DeletePictureUseCase deletePictureUseCase;

    @BeforeEach
    void setUp() {
        deletePictureUseCase = new DeletePictureUseCase(pictureRepository);
    }

    @Test
    void execute_success() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        Picture picture = new Picture();
        picture.setId(pictureId);

        when(pictureRepository.findById(pictureId)).thenReturn(Optional.of(picture));

        // Act
        deletePictureUseCase.execute(pictureId);

        // Assert
        verify(pictureRepository).findById(pictureId);
        verify(pictureRepository).delete(pictureId);
    }

    @Test
    void execute_picture_not_found_throws_exception() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        when(pictureRepository.findById(pictureId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> deletePictureUseCase.execute(pictureId));
        verify(pictureRepository).findById(pictureId);
        verify(pictureRepository, never()).delete(pictureId);
    }

    @Test
    void execute_with_valid_id() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        Picture picture = new Picture();
        picture.setId(pictureId);

        when(pictureRepository.findById(pictureId)).thenReturn(Optional.of(picture));

        // Act
        assertDoesNotThrow(() -> deletePictureUseCase.execute(pictureId));

        // Assert
        verify(pictureRepository).findById(pictureId);
        verify(pictureRepository).delete(pictureId);
    }

    @Test
    void execute_multiple_deletes() {
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
        deletePictureUseCase.execute(pictureId1);
        deletePictureUseCase.execute(pictureId2);

        // Assert
        verify(pictureRepository).delete(pictureId1);
        verify(pictureRepository).delete(pictureId2);
    }

    @Test
    void execute_throws_correct_exception_message() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        when(pictureRepository.findById(pictureId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deletePictureUseCase.execute(pictureId)
        );
        assertTrue(exception.getMessage().contains("Picture not found"));
    }
}

