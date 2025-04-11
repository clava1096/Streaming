package com.clava1096.musicstreaming.services;

import com.clava1096.musicstreaming.mappers.ArtistMapper;
import com.clava1096.musicstreaming.models.Artist;
import com.clava1096.musicstreaming.models.ArtistRequest;
import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.dto.ArtistRequestDTO;
import com.clava1096.musicstreaming.models.enumpack.RequestStatus;
import com.clava1096.musicstreaming.models.enumpack.UserRole;
import com.clava1096.musicstreaming.models.repositories.ArtistRepository;
import com.clava1096.musicstreaming.models.repositories.ArtistRequestRepository;
import com.clava1096.musicstreaming.models.repositories.TrackRepository;
import com.clava1096.musicstreaming.models.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final ArtistRequestRepository artistRequestRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<ArtistRequestDTO> getArtistRequests() {
        return artistRequestRepository.findAllByStatus(RequestStatus.PENDING).stream()
                .map(request -> ArtistRequestDTO.builder()
                        .id(request.getId())
                        .userId((long) request.getUser().getId())
                        .additionalInfo(request.getAdditionalInfo())
                        .type(request.getType())
                        .status(request.getStatus())
                        .createdAt(request.getCreatedAt())
                        .build())
                .toList();
    }

    @Transactional
    public void approveArtist(Long requestId) {
        ArtistRequest request = artistRequestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Зарос не найден с ID: " + requestId));

        User user = request.getUser();
        user.setUserRole(UserRole.ROLE_ARTIST);

        userRepository.save(user);
        LocalDateTime now = LocalDateTime.now();

        Artist artist = Artist.builder()
                .user(user)
                .name(user.getName())
                .birthDate(now.toLocalDate())
                .pseudonym(user.getUsername())
                .surname(user.getUsername())
                .build();

        artistRepository.save(artist);
        request.setStatus(RequestStatus.APPROVED);
        artistRequestRepository.save(request);

        log.info("Approved artist request ID: {}", requestId);
    }
}
