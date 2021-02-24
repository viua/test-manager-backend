package sb.testmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.controller.exceptions.AlreadyExistException;
import sb.testmanager.model.TestDefinition;
import sb.testmanager.repository.TestDefinitionRepository;

@Service
@RequiredArgsConstructor
public class TestSpecificationService {

    private final TestDefinitionRepository testDefinitionRepository; /// rm TestSpecificationRepository

    @Transactional
    public TestSpecDto addTestSpecification(TestSpecDto dto) {
        testDefinitionRepository.findByName(dto.getName())
                .ifPresent(e -> { throw AlreadyExistException.testSpecAlreadyExists(); });
        TestDefinition testDefinition = testDefinitionRepository.save(TestDefinition.of(dto.getName()));
        /// -> map to DTO
        return new TestSpecDto(testDefinition.getName());
    }

    //TestDefinition
}
