package spring.template.learn.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.learn.dto.ResMessageDto;
import spring.template.learn.dto.actor.ActorResponseDto;
import spring.template.learn.dto.actor.UpsertActorRequestDto;
import spring.template.learn.service.ActorService;

import java.util.List;

@RestController
@RequestMapping("/v1/actors")
@RequiredArgsConstructor
@Validated
public class ActorController {

    private final ActorService actorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResMessageDto<Void> createActor(
            @Validated
            @RequestBody UpsertActorRequestDto request
    ) {
        return actorService.createActor(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
        ResMessageDto<List<ActorResponseDto>> getAllActor() {
        return actorService.getAllActor();
    }

}
