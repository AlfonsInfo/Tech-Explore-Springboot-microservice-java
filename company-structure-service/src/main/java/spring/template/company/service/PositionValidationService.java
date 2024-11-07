package spring.template.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.template.company.constant.MessageValidator;
import spring.template.company.dto.request.position.ReqUpsertPositionDto;
import spring.template.company.repository.PositionRepository;

@Service
@RequiredArgsConstructor
public class PositionValidationService {
    private final PositionRepository positionRepository;

    public void validateNewPositionIsUnique(ReqUpsertPositionDto request) {
        if (positionRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MessageValidator.DEPARTMENT_NAME_ALREADY_TAKEN
            );
        }
    }

    public void validateUpdatePositionIsUnique(ReqUpsertPositionDto request, Long id) {
        if (positionRepository.existByNameAndIdNot(request.getName(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MessageValidator.DEPARTMENT_NAME_ALREADY_TAKEN
            );
        }
    }

}
