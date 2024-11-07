package spring.template.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.template.company.dto.projection.DepartmentSummary;
import spring.template.company.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
        List<DepartmentSummary> findAllBy();
        //Exist by department name
        boolean existsByName(String name);
        //Check if name is unique or owned by id
        @Query("SELECT CASE WHEN COUNT(d) > 0 THEN false ELSE true END FROM DepartmentEntity d WHERE d.name = :name AND d.id <> :id")
        boolean isNameUniqueOrOwnedById(String name, Long id);
}
