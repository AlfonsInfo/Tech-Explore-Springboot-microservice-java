package spring.template.company.mapper;

import org.mapstruct.Mapper;
import spring.template.company.dto.projection.DepartmentSummary;
import spring.template.company.dto.request.department.ReqCreateDepartmentDto;
import spring.template.company.dto.request.department.ReqUpdateDepartmentDto;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.dto.response.ResDepartmentDto;
import spring.template.company.entity.DepartmentEntity;
import spring.template.company.entity.PositionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionEntity toPositionEntityFromCreateDto(ReqUpsertPositionDto request);

}
