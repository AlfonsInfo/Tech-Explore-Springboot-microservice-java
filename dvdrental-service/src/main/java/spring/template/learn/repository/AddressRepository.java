package spring.template.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.template.learn.entity.ActorEntity;
import spring.template.learn.entity.AddressEntity;


@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
