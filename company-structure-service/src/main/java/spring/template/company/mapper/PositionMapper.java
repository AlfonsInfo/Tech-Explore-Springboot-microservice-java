package spring.template.company.mapper;

import org.mapstruct.Mapper;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.dto.response.ResPositionDto;
import spring.template.company.entity.PositionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionEntity toPositionEntityFromUpsertDto(ReqUpsertPositionDto request);
    List<ResPositionDto> toResPositionDtoFromEntities(List<PositionEntity> positionEntities);
    ResPositionDto toResPositionDtoFromEntity(PositionEntity positionEntity);
}
