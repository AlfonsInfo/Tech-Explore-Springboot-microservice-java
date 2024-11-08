package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.PostHastagEntity;

import java.util.UUID;

public interface PostHashtagRepository extends JpaRepository<PostHastagEntity, UUID> {

}
