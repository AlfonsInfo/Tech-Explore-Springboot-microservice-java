package spring.template.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.template.company.constant.MessageValidator;
import spring.template.company.dto.request.function.ReqUpsertFunctionDto;
import spring.template.company.repository.FunctionRepository;

@Service
@RequiredArgsConstructor
public class FunctionValidationService {
    private final FunctionRepository functionRepository;

    public void validateNewFunctionIsUnique(ReqUpsertFunctionDto request) {
        if (functionRepository.existsByName(request.getName())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MessageValidator.DEPARTMENT_NAME_ALREADY_TAKEN
            );
        }
    }

    public void validateUpdatePositionIsUnique(ReqUpsertFunctionDto request, Long id) {
        if (functionRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    MessageValidator.DEPARTMENT_NAME_ALREADY_TAKEN
            );
        }
    }

}
