package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.PostHashtagEntity;
import spring.template.mediasocial.enums.PostEnum;

import java.util.List;
import java.util.UUID;

public interface PostHashtagRepository extends JpaRepository<PostHashtagEntity, Long> {
    List<PostHashtagEntity> findAllByHashTagEntity_IdAndPost_Status (Long tag, PostEnum status);
}
