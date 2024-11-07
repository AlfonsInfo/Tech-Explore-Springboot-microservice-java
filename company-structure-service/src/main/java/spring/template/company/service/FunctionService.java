package spring.template.company.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import spring.template.company.dto.request.function.ReqUpsertFunctionDto;
import spring.template.company.dto.response.ResFunctionDto;
import spring.template.company.entity.DepartmentEntity;
import spring.template.company.entity.FunctionEntity;
import spring.template.company.mapper.FunctionMapper;
import spring.template.company.repository.FunctionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FunctionService {
    private final FunctionRepository functionRepository;
    private final FunctionMapper functionMapper;
    private final FunctionValidationService functionValidationService;

    public void createFunction(
            @RequestBody @Valid ReqUpsertFunctionDto request
    ) {
        functionValidationService.validateNewFunctionIsUnique(request);
        FunctionEntity functionEntity = functionMapper.toFunctionEntityFromUpsertDto(request);
        functionEntity.setDepartment(DepartmentEntity.builder().id(request.getDepartmentId()).build());
        functionRepository.save(functionEntity);
    }

    public void updateFunction(
            @RequestBody @Valid ReqUpsertFunctionDto request,
             Long id
    ) {
        functionValidationService.validateUpdatePositionIsUnique(request, id);
        FunctionEntity functionEntity = functionMapper.toFunctionEntityFromUpsertDto(request);
        functionEntity.setId(id);
        functionEntity.setDepartment(DepartmentEntity.builder().id(request.getDepartmentId()).build());
        functionRepository.save(functionEntity);
    }

    public void deletePosition(Long id) {
        functionRepository.deleteById(id);
    }

    //Get Position
    public ResFunctionDto getFunction(Long id, boolean isIncludeDepartment) {
        FunctionEntity functionEntity = functionRepository.findById(id).orElseThrow();
        ResFunctionDto resPositionDto = functionMapper.toResFunctionDtoFromEntity(functionEntity);
        if(isIncludeDepartment){
            resPositionDto.setDepartmentId(functionEntity.getDepartment().getId());
            resPositionDto.setDepartmentName(functionEntity.getDepartment().getName());
        }
        return resPositionDto;
    }

    //Get List of Positions
    public List<ResFunctionDto>     getFunctions(boolean isIncludeDepartment) {
        List<FunctionEntity> functionEntities = functionRepository.findAll();
        return functionEntities
                        .stream()
                        .map(
                                functionEntity -> toResFunctionDtoFromEntity(functionEntity, isIncludeDepartment)
                        )
                        .toList();
    }


    //Mapping method
    private ResFunctionDto toResFunctionDtoFromEntity(FunctionEntity functionEntity, boolean isIncludeDepartment) {
            ResFunctionDto resPositionDto = functionMapper.toResFunctionDtoFromEntity(functionEntity);
            if(isIncludeDepartment){
                resPositionDto.setDepartmentId(functionEntity.getDepartment().getId());
                resPositionDto.setDepartmentName(functionEntity.getDepartment().getName());
            }
            return resPositionDto;
    }

}
