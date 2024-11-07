package spring.template.company.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.company.constant.MessageResponse;
import spring.template.company.constant.RequestParamConstant;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.dto.response.ResMessageDto;
import spring.template.company.dto.response.ResPositionDto;
import spring.template.company.service.PositionService;
import spring.template.company.validation.annotation.PositionIdIsFound;

import java.util.List;

@RestController
@RequestMapping("/v1/positions")
@RequiredArgsConstructor
@Validated
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createPosition(
            @RequestBody @Valid ReqUpsertPositionDto request
    ) {
        positionService.createPosition(request);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.POSITION_CREATED)
                .statusCode(201)
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<Void> updatePosition(
            @RequestBody @Valid ReqUpsertPositionDto request,
            @PathVariable @PositionIdIsFound Long id
    ) {
        positionService.updatePosition(request, id);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.POSITION_UPDATED)
                .statusCode(200)
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<Void> deletePosition(
            @PathVariable  @PositionIdIsFound Long id
    ) {
        positionService.deletePosition(id);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.POSITION_DELETED)
                .statusCode(200)
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<ResPositionDto> getPosition(
            @PathVariable  @PositionIdIsFound Long id,
            @RequestParam(
                    value= RequestParamConstant.INCLUDE_DEPARTMENT,
                    required = false,
                    defaultValue = "false"
            ) Boolean includeDepartment
    ) {
        return ResMessageDto.<ResPositionDto>builder()
                .message(MessageResponse.POSITION_READ)
                .data(positionService.getPosition(id,includeDepartment))
                .statusCode(200)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<List<ResPositionDto>> getPositions(
            @RequestParam(
                    value=RequestParamConstant.INCLUDE_DEPARTMENT,
                    required = false,
                    defaultValue = "false"
            ) Boolean includeDepartment
    ) {
        return ResMessageDto.<List<ResPositionDto>>builder()
                .message(MessageResponse.POSITION_READ)
                .data(positionService.getPositions(includeDepartment))
                .statusCode(200)
                .build();
    }

}
