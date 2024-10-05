package spring.template.learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.template.learn.entity.DiscountEntity;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
    boolean existsByCodeAndIsActive(String code, Boolean isActive);
}
