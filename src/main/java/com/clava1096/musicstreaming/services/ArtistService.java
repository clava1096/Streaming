package com.clava1096.musicstreaming.services;

import com.clava1096.musicstreaming.exceptions.custom.NotFoundInDBException;
import com.clava1096.musicstreaming.mappers.ArtistMapper;
import com.clava1096.musicstreaming.models.Artist;
import com.clava1096.musicstreaming.models.ArtistRequest;
import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.dto.*;
import com.clava1096.musicstreaming.models.enumpack.RequestStatus;
import com.clava1096.musicstreaming.models.enumpack.RequestType;
import com.clava1096.musicstreaming.models.enumpack.UserRole;
import com.clava1096.musicstreaming.models.repositories.ArtistRepository;
import com.clava1096.musicstreaming.models.repositories.ArtistRequestRepository;
import com.clava1096.musicstreaming.models.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRequestRepository artistRequestRepository;

    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    private final UserRepository userRepository;

    @Transactional
    public ArtistDTO createArtist(ArtistSaveDTO artistSaveDTO) {
        Artist artist = artistMapper.toArtist(artistSaveDTO);
        log.info(artist.toString());
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
    }

    @Transactional
    public ArtistRequestDTO createPromotionRequest(ArtistPromotionRequestDTO artistPromotionRequestDTO) {
        User user = userRepository.findById(artistPromotionRequestDTO.getUserId())
                .orElseThrow(() -> new NotFoundInDBException("Пользователь не найден по id: " + artistPromotionRequestDTO.getUserId()));

        if (user.getUserRole() == UserRole.ROLE_ARTIST) {
            throw new NotFoundInDBException("Пользователь уже артист");
        }

        if (artistRequestRepository.existsByUserAndStatus(user, RequestStatus.PENDING)) {
            throw new NotFoundInDBException("Пользователь уже отправил запрос на повышение");
        }

        ArtistRequest request = ArtistRequest.builder()
                .user(user)
                .type(RequestType.PROMOTION)
                .status(RequestStatus.PENDING)
                .additionalInfo(artistPromotionRequestDTO.getAdditionalInfo())
                .build();

        artistRequestRepository.save(request);
        return ArtistRequestDTO.builder()
                .id(request.getId())
                .type(request.getType())
                .status(request.getStatus())
                .userId((long) request.getUser().getId())
                .additionalInfo(request.getAdditionalInfo())
                .createdAt(request.getCreatedAt())
                .build();
    }
}
