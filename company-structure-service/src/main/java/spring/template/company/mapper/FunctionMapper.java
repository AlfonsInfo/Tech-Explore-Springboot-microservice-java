package spring.template.company.mapper;

import org.mapstruct.Mapper;
import spring.template.company.dto.request.function.ReqUpsertFunctionDto;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.dto.response.ResFunctionDto;
import spring.template.company.dto.response.ResPositionDto;
import spring.template.company.entity.FunctionEntity;
import spring.template.company.entity.PositionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FunctionMapper {
     FunctionEntity toFunctionEntityFromUpsertDto(ReqUpsertFunctionDto request);
     List<ResFunctionDto> toResFunctionDtoFromEntities(List<FunctionEntity> functionEntities);
    ResFunctionDto toResFunctionDtoFromEntity(FunctionEntity functionEntity);
}
