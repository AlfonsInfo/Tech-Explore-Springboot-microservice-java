package spring.template.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.template.learn.entity.BrandEntity;


@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    //exist
    Boolean existsByName(String name);

    //find
}
