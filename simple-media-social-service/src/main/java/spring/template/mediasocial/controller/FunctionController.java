package spring.template.mediasocial.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.mediasocial.constant.MessageResponse;
import spring.template.mediasocial.constant.RequestParamConstant;
import spring.template.mediasocial.dto.request.function.ReqUpsertFunctionDto;
import spring.template.mediasocial.dto.response.ResFunctionDto;
import spring.template.mediasocial.dto.response.ResMessageDto;
import spring.template.mediasocial.validation.annotation.PositionIdIsFound;

import java.util.List;

@RestController
@RequestMapping("/v1/functions")
@RequiredArgsConstructor
@Validated
public class FunctionController {

    private final FunctionService functionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResMessageDto<Void> createPosition(
            @RequestBody @Valid ReqUpsertFunctionDto request
    ) {
        functionService.createFunction(request);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.FUNCTION_CREATED)
                .statusCode(201)
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<Void> updatePosition(
            @RequestBody @Valid ReqUpsertFunctionDto request,
            @PathVariable @PositionIdIsFound Long id
    ) {
        functionService.updateFunction(request, id);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.FUNCTION_UPDATED)
                .statusCode(200)
                .build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<Void> deletePosition(
            @PathVariable  @PositionIdIsFound Long id
    ) {
        functionService.deletePosition(id);
        return ResMessageDto.<Void>builder()
                .message(MessageResponse.FUNCTION_DELETED)
                .statusCode(200)
                .build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<ResFunctionDto> getPosition(
            @PathVariable  @PositionIdIsFound Long id,
            @RequestParam(
                    value= RequestParamConstant.INCLUDE_DEPARTMENT,
                    required = false,
                    defaultValue = "false"
            ) Boolean includeDepartment
    ) {
        return ResMessageDto.<ResFunctionDto>builder()
                .message(MessageResponse.FUNCTION_READ)
                .data(functionService.getFunction(id,includeDepartment))
                .statusCode(200)
                .build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResMessageDto<List<ResFunctionDto>> getPositions(
            @RequestParam(
                    value=RequestParamConstant.INCLUDE_DEPARTMENT,
                    required = false,
                    defaultValue = "false"
            ) Boolean includeDepartment
    ) {
        return ResMessageDto.<List<ResFunctionDto>>builder()
                .message(MessageResponse.FUNCTION_READ)
                .data(functionService.getFunctions(includeDepartment))
                .statusCode(200)
                .build();
    }

}
