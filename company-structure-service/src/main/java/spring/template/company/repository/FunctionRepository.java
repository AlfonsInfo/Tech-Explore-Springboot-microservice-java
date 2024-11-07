package spring.template.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.template.company.dto.projection.DepartmentSummary;
import spring.template.company.entity.DepartmentEntity;
import spring.template.company.entity.FunctionEntity;

import java.util.List;

public interface FunctionRepository extends JpaRepository<FunctionEntity, Long> {
        List<DepartmentSummary> findAllBy();
        boolean existsByName(String name);
        @Query("SELECT CASE WHEN COUNT(d) > 0 THEN false ELSE true END FROM FunctionEntity d WHERE d.name = :name AND d.id <> :id")
        boolean isNameUniqueOrOwnedById(String name, Long id);
}
