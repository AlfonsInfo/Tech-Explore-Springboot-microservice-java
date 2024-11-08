package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.LikeEntity;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<LikeEntity, UUID> {
    boolean existsByUser_IdAndPost_Id(UUID userId, UUID postId);
}
