package spring.template.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.template.company.dto.projection.DepartmentSummary;
import spring.template.company.dto.request.department.ReqCreateDepartmentDto;
import spring.template.company.dto.request.department.ReqUpdateDepartmentDto;
import spring.template.company.dto.response.ResDepartmentDto;
import spring.template.company.dto.response.ResPositionDto;
import spring.template.company.entity.DepartmentEntity;
import spring.template.company.entity.PositionEntity;
import spring.template.company.mapper.DepartmentMapper;
import spring.template.company.mapper.PositionMapper;
import spring.template.company.repository.DepartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentValidationService departmentValidationService;

    //mapper
    private final DepartmentMapper departmentMapper;
    private final PositionMapper positionMapper;
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

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
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

    public ResDepartmentDto getDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElse(null);
        return departmentMapper.toResDepartmentDto(departmentEntity);
    }

    public List<ResPositionDto> getPositionsByDepartmentId(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id).orElseThrow();
        List< PositionEntity> listPositionEntity = departmentEntity.getPositions();
         return positionMapper.toResPositionDtoFromEntities(listPositionEntity);
    }



}
