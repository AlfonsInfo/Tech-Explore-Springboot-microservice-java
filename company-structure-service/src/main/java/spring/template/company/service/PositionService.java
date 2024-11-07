package spring.template.company.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.dto.response.ResPositionDto;
import spring.template.company.entity.DepartmentEntity;
import spring.template.company.entity.PositionEntity;
import spring.template.company.mapper.PositionMapper;
import spring.template.company.repository.PositionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;
    private final PositionValidationService positionValidationService;

    public void createPosition(
            @RequestBody @Valid ReqUpsertPositionDto request
    ) {
        positionValidationService.validateNewPositionIsUnique(request);
        PositionEntity positionEntity = positionMapper.toPositionEntityFromUpsertDto(request);
        positionEntity.setDepartment(DepartmentEntity.builder().id(request.getDepartmentId()).build());
        positionRepository.save(positionEntity);
    }

    public void updatePosition(
            @RequestBody @Valid ReqUpsertPositionDto request,
             Long id
    ) {
        positionValidationService.validateUpdatePositionIsUnique(request, id);
        PositionEntity positionEntity = positionMapper.toPositionEntityFromUpsertDto(request);
        positionEntity.setId(id);
        positionEntity.setDepartment(DepartmentEntity.builder().id(request.getDepartmentId()).build());
        positionRepository.save(positionEntity);
    }

    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }

    //Get Position
    public ResPositionDto getPosition(Long id, boolean isIncludeDepartment) {
        PositionEntity positionEntity = positionRepository.findById(id).orElseThrow();
        ResPositionDto resPositionDto = positionMapper.toResPositionDtoFromEntity(positionEntity);
        if(isIncludeDepartment){
            resPositionDto.setDepartmentId(positionEntity.getDepartment().getId());
            resPositionDto.setDepartmentName(positionEntity.getDepartment().getName());
        }
        return resPositionDto;
    }

    //Get List of Positions
    public List<ResPositionDto> getPositions(boolean isIncludeDepartment) {
        List<PositionEntity> positionEntities = positionRepository.findAll();
        return positionEntities
                        .stream()
                        .map(
                                positionEntity -> toResPositionDtoFromEntity(positionEntity, isIncludeDepartment)
                        )
                        .toList();
    }


    //Mapping method
    public ResPositionDto toResPositionDtoFromEntity(PositionEntity positionEntity, boolean isIncludeDepartment) {
            ResPositionDto resPositionDto = positionMapper.toResPositionDtoFromEntity(positionEntity);
            if(isIncludeDepartment){
                resPositionDto.setDepartmentId(positionEntity.getDepartment().getId());
                resPositionDto.setDepartmentName(positionEntity.getDepartment().getName());
            }
            return resPositionDto;
    }

}
