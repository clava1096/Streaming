package com.clava1096.musicstreaming.models.repositories;
import com.clava1096.musicstreaming.models.Artist;
import com.clava1096.musicstreaming.models.ArtistRequest;
import com.clava1096.musicstreaming.models.User;
import com.clava1096.musicstreaming.models.enumpack.RequestStatus;
import com.clava1096.musicstreaming.models.enumpack.RequestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArtistRequestRepository extends JpaRepository<ArtistRequest, Long> {

    // Найти все запросы по типу (PROMOTION/DELETION)
    List<ArtistRequest> findAllByType(RequestType type);

    // Найти все запросы по статусу
    List<ArtistRequest> findAllByStatus(RequestStatus status);

    // Найти запросы конкретного пользователя
    List<ArtistRequest> findAllByUser(User user);

    // Найти запросы для конкретного артиста (для DELETION)
    List<ArtistRequest> findAllByArtist(Artist artist);

    // Найти запросы по типу и статусу
    List<ArtistRequest> findAllByTypeAndStatus(RequestType type, RequestStatus status);

    // Кастомный запрос для поиска активных запросов на повышение
    @Query("SELECT r FROM ArtistRequest r WHERE r.type = 'PROMOTION' AND r.status = 'PENDING'")
    List<ArtistRequest> findPendingPromotionRequests();

    // Найти запрос по ID с подгруженным пользователем
    @Query("SELECT r FROM ArtistRequest r JOIN FETCH r.user WHERE r.id = :id")
    Optional<ArtistRequest> findByIdWithUser(@Param("id") Long id);

    // Проверить существование активного запроса для пользователя
    boolean existsByUserAndStatus(User user, RequestStatus status);
}
