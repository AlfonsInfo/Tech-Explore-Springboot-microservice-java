package spring.template.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.template.learn.entity.ActorEntity;


@Repository
public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

}
