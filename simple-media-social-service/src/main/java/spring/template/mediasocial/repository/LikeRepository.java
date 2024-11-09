package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.template.mediasocial.dto.like.ProjectionLikeCount;
import spring.template.mediasocial.entity.LikeEntity;

import java.util.List;
import java.util.UUID;

public interface LikeRepository extends JpaRepository<LikeEntity, UUID> {
    boolean existsByUser_IdAndPost_Id(UUID userId, UUID postId);

    @Query(value = "select I.post_id as postId, count(*) as \"count\"  from like_post I where I.post_id = :postIds group by I.post_id", nativeQuery = true)
    List<ProjectionLikeCount> countByPost_IdInGroupByPost_id(List<UUID> postIds);
}
