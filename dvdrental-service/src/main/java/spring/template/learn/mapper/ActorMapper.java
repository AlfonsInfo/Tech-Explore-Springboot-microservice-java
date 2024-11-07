package spring.template.learn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.template.learn.dto.actor.ActorResponseDto;
import spring.template.learn.dto.actor.UpsertActorRequestDto;
import spring.template.learn.entity.ActorEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    @Mapping(target = "actorId")
    ActorEntity upsertActorRequestDtoToActorEntity(UpsertActorRequestDto request);

    List<ActorResponseDto> actorEntitiesToActorResponseDtos(List<ActorEntity> actorEntities);
}
