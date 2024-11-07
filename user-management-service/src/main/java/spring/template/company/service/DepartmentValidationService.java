package spring.template.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.template.company.dto.projection.DepartmentSummary;
import spring.template.company.dto.request.ReqCreateDepartmentDto;
import spring.template.company.dto.request.ReqUpdateDepartmentDto;
import spring.template.company.dto.response.ResDepartmentDto;
import spring.template.company.entity.DepartmentEntity;
import spring.template.company.mapper.DepartmentMapper;
import spring.template.company.repository.DepartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentValidationService {

    private final DepartmentRepository departmentRepository;

    public void validateDepartmentNameIsUniqueOrOwnedById(String name, Long id) {
        if (!departmentRepository.isNameUniqueOrOwnedById(name, id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department name must be unique or same as existing");
        }
    }
}
