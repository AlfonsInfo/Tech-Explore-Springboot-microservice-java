package spring.template.mediasocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.mediasocial.entity.PostEntity;
import spring.template.mediasocial.entity.UserEntity;

import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    int countByUser_Id(Long userId);
}
