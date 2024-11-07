package spring.template.company.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.template.company.dto.request.ReqCreateDepartmentDto;
import spring.template.company.dto.request.ReqUpdateDepartmentDto;
import spring.template.company.dto.response.ResDepartmentDto;
import spring.template.company.dto.response.ResMessageDto;
import spring.template.company.service.DepartmentService;
import spring.template.company.validation.annotation.DepartmentIdIsFound;

import java.util.List;

@RestController
@RequestMapping("/v1/departments")
@RequiredArgsConstructor
@Validated
public class DepartmentController {

        private final DepartmentService departmentService;

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public ResMessageDto<Void> createDepartment(
                @RequestBody ReqCreateDepartmentDto request
        ) {
            departmentService.createDepartment(request);
            return ResMessageDto.<Void>builder()
                    .message("Department created successfully")
                    .statusCode(HttpStatus.CREATED.value())
                    .build();
        }

        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResMessageDto<Void> updateDepartment(
                @RequestBody @Valid ReqUpdateDepartmentDto request,
                 @DepartmentIdIsFound @PathVariable Long id
        ) {
            departmentService.updateDepartment(request, id);
            return ResMessageDto.<Void>builder()
                    .message("Department updated successfully")
                    .statusCode(HttpStatus.OK.value())
                    .build();
        }


        @GetMapping
        @ResponseStatus(HttpStatus.OK)
        public ResMessageDto<List<ResDepartmentDto>> getDepartments(
                @RequestParam(value="detail", required = false, defaultValue = "false") Boolean detail
        ) {
            List<ResDepartmentDto> resp  = departmentService.getDepartments(detail);
            return ResMessageDto.<List<ResDepartmentDto>>builder()
                    .message("Department created successfully")
                    .data(resp)
                    .statusCode(HttpStatus.CREATED.value())
                    .build();
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResMessageDto<Void> deleteDepartment(
                @PathVariable Long id
        ) {
            departmentService.deleteDepartment(id);
            return ResMessageDto.<Void>builder()
                    .message("Department deleted successfully")
                    .statusCode(HttpStatus.NO_CONTENT.value())
                    .build();
        }


}
