package spring.template.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;
    private final DepartmentValidationService departmentValidationService;

    public void createDepartment(ReqCreateDepartmentDto request) {
        DepartmentEntity departmentEntity = departmentMapper.toDepartmentEntityFromCreateDto(request);
        departmentRepository.save(departmentEntity);
    }


    public void updateDepartment(
            ReqUpdateDepartmentDto request,
            Long id
    ) {
        //validation
        departmentValidationService.validateDepartmentNameIsUniqueOrOwnedById(request.getName(), id);
        //saving
        DepartmentEntity departmentEntity = departmentMapper.toDepartmentEntityFromUpdateDto(request);
        departmentEntity.setId(id);
        departmentRepository.save(departmentEntity);
    }


    public List<ResDepartmentDto> getDepartments(Boolean detail) {
        if (detail) {
            List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
            return departmentMapper.toResDepartmentDtosFromEntity(departmentEntities);
        } else {
            List<DepartmentSummary> departmentSummaries = departmentRepository.findAllBy();
            return departmentMapper.toResDepartmentDtosFromSummary(departmentSummaries);
        }
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }


}
