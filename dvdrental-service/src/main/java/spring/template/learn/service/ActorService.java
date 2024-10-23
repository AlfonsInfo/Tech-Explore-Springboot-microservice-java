package spring.template.learn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.actor.ActorResponseDto;
import spring.template.learn.dto.actor.UpsertActorRequestDto;
import spring.template.learn.entity.ActorEntity;
import spring.template.learn.mapper.ActorMapper;
import spring.template.learn.repository.ActorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {
    //mapper
    private final ActorMapper actorMapper;

    //repository
    private final ActorRepository actorRepository;


    public ResMessageDto<Void> createActor(UpsertActorRequestDto request) {
        ActorEntity actorEntity = actorMapper.upsertActorRequestDtoToActorEntity(request);
        actorRepository.save(actorEntity);
        return ResMessageDto.<Void>builder()
                .message("Actor created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    public ResMessageDto<List<ActorResponseDto>> getAllActor() {
        List<ActorEntity> actorEntities = actorRepository.findAll();
        List<ActorResponseDto> actorResponseDtos = actorMapper.actorEntitiesToActorResponseDtos(actorEntities);
        return ResMessageDto.<List<ActorResponseDto>>builder()
                .data(actorResponseDtos)
                .message("Get all actor success")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
