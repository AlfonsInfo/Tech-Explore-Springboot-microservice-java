package spring.template.company.mapper;

import org.mapstruct.Mapper;
import spring.template.company.dto.projection.DepartmentSummary;
import spring.template.company.dto.request.ReqCreateDepartmentDto;
import spring.template.company.dto.request.ReqUpdateDepartmentDto;
import spring.template.company.dto.response.ResDepartmentDto;
import spring.template.company.entity.DepartmentEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    ResDepartmentDto toResDepartmentDto(spring.template.company.entity.DepartmentEntity departmentEntity);
    DepartmentEntity toDepartmentEntityFromCreateDto(ReqCreateDepartmentDto reqCreateDepartmentDto);
    DepartmentEntity toDepartmentEntityFromUpdateDto(ReqUpdateDepartmentDto reqUpdateDepartmentDto);
    List<ResDepartmentDto> toResDepartmentDtosFromSummary(List<DepartmentSummary> departments);
    List<ResDepartmentDto> toResDepartmentDtosFromEntity(List<DepartmentEntity> departments);
}
