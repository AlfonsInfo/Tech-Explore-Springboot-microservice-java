package spring.template.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.entity.PositionEntity;
import spring.template.company.mapper.PositionMapper;
import spring.template.company.repository.PositionRepository;
@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;


    public void createPosition(ReqUpsertPositionDto request) {
        PositionEntity positionEntity = positionMapper.toPositionEntityFromCreateDto(request);
        positionRepository.save(positionEntity);
    }
}
