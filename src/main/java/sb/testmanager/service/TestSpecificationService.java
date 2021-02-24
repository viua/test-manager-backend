package sb.testmanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.controller.exceptions.AlreadyExistException;
import sb.testmanager.model.TestDefinition;
import sb.testmanager.repository.TestDefinitionRepository;
import sb.testmanager.service.mapper.TestRunMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestSpecificationService {

    private final TestRunMapper testRunMapper;
    private final TestDefinitionRepository testDefinitionRepository; /// rm TestSpecificationRepository

    @Transactional
    public TestSpecDto addTestSpecification(TestSpecDto dto) {
        testDefinitionRepository.findByName(dto.getName())
                .ifPresent(e -> { throw AlreadyExistException.testSpecAlreadyExists(); });
        TestDefinition testDefinition = testDefinitionRepository.save(TestDefinition.of(dto.getName()));
        //TODO map 2 DTO
        return new TestSpecDto(testDefinition.getName());
    }

    @Transactional(readOnly = true)
    public List<TestSpecDto> getTests() { //TestRunDto
        return testDefinitionRepository.findAll().stream()
                .map(TestRunMapper::map)
                .collect(Collectors.toList());
    }

}
