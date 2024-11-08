package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.PostHastagEntity;
import spring.template.mediasocial.enums.PostEnum;

import java.util.List;
import java.util.UUID;

public interface PostHashtagRepository extends JpaRepository<PostHastagEntity, UUID> {
    List<PostHastagEntity> findAllByHashTagEntity_IdAndPost_Status (UUID tag, PostEnum status);
}
