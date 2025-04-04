package com.clava1096.musicstreaming.services;

import com.clava1096.musicstreaming.exceptions.custom.NotFoundInDBException;
import com.clava1096.musicstreaming.mappers.ArtistMapper;
import com.clava1096.musicstreaming.models.Album;
import com.clava1096.musicstreaming.models.Artist;
import com.clava1096.musicstreaming.models.ArtistRequest;
import com.clava1096.musicstreaming.models.dto.*;
import com.clava1096.musicstreaming.models.enumpack.RequestStatus;
import com.clava1096.musicstreaming.models.enumpack.RequestType;
import com.clava1096.musicstreaming.models.repositories.AlbumRepository;
import com.clava1096.musicstreaming.models.repositories.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    private final AlbumRepository albumRepository;

    @Transactional
    public ArtistDTO createArtist(ArtistSaveDTO artistSaveDTO) {
        Artist artist = artistMapper.toArtist(artistSaveDTO);
        artistRepository.save(artist);
        return artistMapper.toArtistDTO(artist);
    }

    @Transactional
    public ArtistDTO deleteArtistById(UUID id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        if (artist == null) return null;
        artistRepository.delete(artist);
        return artistMapper.toArtistDTO(artist);
    }

    @Transactional(readOnly = true)
    public List<ArtistDTO> getArtistByName(String name) {
        List<Artist> artists = artistRepository.searchByName(name).orElseThrow(() -> new NotFoundInDBException("Не найдена артист по имени = "+ name));
        return artistMapper.toArtistDTOs(artists);
    }

    @Transactional(readOnly = true)
    public ArtistDTO getArtistById(UUID id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NotFoundInDBException("Не найден Артист по id = "+ id));
        return artistMapper.toArtistDTO(artist);
    }

    //запрос на удаление
    @Transactional
    public ArtistRequestDTO createDeletionRequest(ArtistDeletionRequestDTO dto) {
        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new NotFoundInDBException("Не найден Артист по id = "+ dto.getArtistId()));

        ArtistRequest request = ArtistRequest.builder()
                .user(artist.getUser())
                .artist(artist)
                .type(RequestType.DELETION)
                .status(RequestStatus.PENDING)
                .reason(dto.getReason())
                .build();

        return null;
        //requestRepo.save(request);
        //return convertToDTO(request);
    }

    // запрос на повышение
    @Transactional
    public ArtistDTO approvePromotionRequest(UUID requestId) {
        ArtistRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new NotFoundInDBException("Не найден Артист по id = " + requestId));

        if (request.getType() != RequestType.PROMOTION) {
            throw new IllegalStateException("Not a promotion request");
        }

        User user = request.getUser();
        Artist artist = Artist.builder()
                .name(user.getName())
                .user(user)
                .build();

        artistRepo.save(artist);
        user.setUserRole(UserRole.ARTIST);
        request.setStatus(RequestStatus.APPROVED);

        return artistMapper.toDTO(artist);
    }

    // запрос на удаление
    //    @Transactional
    //    public void approveDeletionRequest(Long requestId) {
    //        ArtistRequest request = requestRepo.findById(requestId)
    //                .orElseThrow(() -> new EntityNotFoundException("Request not found"));
    //
    //        if (request.getType() != RequestType.DELETION) {
    //            throw new IllegalStateException("Not a deletion request");
    //        }
    //
    //        Artist artist = request.getArtist();
    //        artist.setDeleted(true);  // Мягкое удаление
    //        request.setStatus(RequestStatus.APPROVED);
    //    }

}
