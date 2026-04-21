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
class UpdatePictureUseCaseTest {

    @Mock
    private PictureRepository pictureRepository;

    private UpdatePictureUseCase updatePictureUseCase;

    @BeforeEach
    void setUp() {
        updatePictureUseCase = new UpdatePictureUseCase(pictureRepository);
    }

    @Test
    void execute_success() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        
        Picture existingPicture = new Picture();
        existingPicture.setId(pictureId);
        existingPicture.setTitle("Old Title");
        existingPicture.setDurationSeconds(0);

        Picture updatedPicture = new Picture();
        updatedPicture.setTitle("New Title");
        updatedPicture.setDurationSeconds(0);
        updatedPicture.setUrl("http://example.com/new-picture.jpg");

        Picture savedPicture = new Picture();
        savedPicture.setId(pictureId);
        savedPicture.setTitle("New Title");
        savedPicture.setDurationSeconds(0);
        savedPicture.setUrl("http://example.com/new-picture.jpg");

        when(pictureRepository.findById(pictureId)).thenReturn(Optional.of(existingPicture));
        when(pictureRepository.save(updatedPicture)).thenReturn(savedPicture);

        // Act
        UUID result = updatePictureUseCase.execute(pictureId, updatedPicture);

        // Assert
        assertEquals(pictureId, result);
        verify(pictureRepository).findById(pictureId);
        verify(pictureRepository).save(updatedPicture);
    }

    @Test
    void execute_picture_not_found_throws_exception() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        Picture updatedPicture = new Picture();

        when(pictureRepository.findById(pictureId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> updatePictureUseCase.execute(pictureId, updatedPicture));
        verify(pictureRepository).findById(pictureId);
        verify(pictureRepository, never()).save(updatedPicture);
    }

    @Test
    void execute_updates_all_fields() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        UUID campaignId = UUID.randomUUID();

        Picture existingPicture = new Picture();
        existingPicture.setId(pictureId);

        Picture updatedPicture = new Picture();
        updatedPicture.setTitle("Updated Title");
        updatedPicture.setDurationSeconds(0);
        updatedPicture.setUrl("http://example.com/updated.jpg");
        updatedPicture.setCampaignId(campaignId);

        Picture savedPicture = new Picture();
        savedPicture.setId(pictureId);
        savedPicture.setTitle("Updated Title");
        savedPicture.setDurationSeconds(0);
        savedPicture.setUrl("http://example.com/updated.jpg");
        savedPicture.setCampaignId(campaignId);

        when(pictureRepository.findById(pictureId)).thenReturn(Optional.of(existingPicture));
        when(pictureRepository.save(updatedPicture)).thenReturn(savedPicture);

        // Act
        UUID result = updatePictureUseCase.execute(pictureId, updatedPicture);

        // Assert
        assertEquals(pictureId, result);
        assertTrue(updatedPicture.getId().equals(pictureId));
    }

    @Test
    void execute_multiple_updates() {
        // Arrange
        UUID pictureId = UUID.randomUUID();
        
        Picture existingPicture = new Picture();
        existingPicture.setId(pictureId);

        Picture firstUpdate = new Picture();
        firstUpdate.setTitle("First Update");

        Picture secondUpdate = new Picture();
        secondUpdate.setTitle("Second Update");

        when(pictureRepository.findById(pictureId))
                .thenReturn(Optional.of(existingPicture))
                .thenReturn(Optional.of(existingPicture));

        when(pictureRepository.save(firstUpdate)).thenReturn(firstUpdate);
        when(pictureRepository.save(secondUpdate)).thenReturn(secondUpdate);

        // Act
        UUID result1 = updatePictureUseCase.execute(pictureId, firstUpdate);
        UUID result2 = updatePictureUseCase.execute(pictureId, secondUpdate);

        // Assert
        assertEquals(pictureId, result1);
        assertEquals(pictureId, result2);
        verify(pictureRepository, times(2)).findById(pictureId);
        verify(pictureRepository).save(firstUpdate);
        verify(pictureRepository).save(secondUpdate);
    }
}

