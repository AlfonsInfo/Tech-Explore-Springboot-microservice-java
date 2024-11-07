package spring.template.company.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.template.company.constant.MessageResponse;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.dto.response.ResMessageDto;
import spring.template.company.service.PositionService;

@RestController
@RequestMapping("/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createPosition(
            @RequestBody ReqUpsertPositionDto request
    ) {
        positionService.createPosition(request);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.POSITION_CREATED)
                .statusCode(201)
                .build();
    }
}
