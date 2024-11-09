package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.HashTagEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HashtagRepository extends JpaRepository<HashTagEntity, UUID> {
        //exist
        boolean existsByTag(String tag);

        //find all by tag in
        List<HashTagEntity> findAllByTagIn(List<String> tags);

        Optional<HashTagEntity> findByTag(String tag);
}
