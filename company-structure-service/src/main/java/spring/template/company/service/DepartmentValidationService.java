package spring.template.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import spring.template.company.repository.DepartmentRepository;

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
