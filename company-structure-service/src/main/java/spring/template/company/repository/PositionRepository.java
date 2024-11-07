package spring.template.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.template.company.dto.projection.DepartmentSummary;
import spring.template.company.entity.DepartmentEntity;
import spring.template.company.entity.PositionEntity;

import java.util.List;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
        List<DepartmentSummary> findAllBy();
        boolean existsByName(String name);
        @Query("SELECT CASE WHEN COUNT(d) > 0 THEN false ELSE true END FROM PositionEntity d WHERE d.name = :name AND d.id <> :id")
        boolean isNameUniqueOrOwnedById(String name, Long id);
}
