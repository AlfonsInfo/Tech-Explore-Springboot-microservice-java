package spring.template.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.template.company.dto.ReqUpsertDepartmentDto;
import spring.template.company.dto.ResMessageDto;

@RestController
@RequestMapping("/v1/departments")
public class DepartmentController {

        public ResMessageDto<Void> createDepartment(ReqUpsertDepartmentDto request) {

            return null;
        }
}
