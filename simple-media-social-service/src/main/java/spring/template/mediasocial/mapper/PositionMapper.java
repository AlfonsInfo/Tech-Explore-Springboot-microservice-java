package spring.template.mediasocial.mapper;

import org.mapstruct.Mapper;
import spring.template.mediasocial.dto.request.position.ReqUpsertPositionDto;
import spring.template.mediasocial.dto.response.ResPositionDto;
import spring.template.mediasocial.entity.PositionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionEntity toPositionEntityFromUpsertDto(ReqUpsertPositionDto request);
    List<ResPositionDto> toResPositionDtoFromEntities(List<PositionEntity> positionEntities);
    ResPositionDto toResPositionDtoFromEntity(PositionEntity positionEntity);
}
