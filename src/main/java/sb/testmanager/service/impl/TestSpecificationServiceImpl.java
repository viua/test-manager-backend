package sb.testmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.controller.exceptions.AlreadyExistException;
import sb.testmanager.model.TestDefinition;
import sb.testmanager.repository.TestDefinitionRepository;
import sb.testmanager.service.TestSpecificationService;
import sb.testmanager.service.mapper.TestRunMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestSpecificationServiceImpl
        implements TestSpecificationService {

    private final TestRunMapper testRunMapper;
    private final TestDefinitionRepository testDefinitionRepository; /// rm TestSpecificationRepository

    @Override
    @Transactional
    public TestSpecDto addTestSpecification(TestSpecDto dto) {
        testDefinitionRepository.findByName(dto.getName())
                .ifPresent(e -> { throw AlreadyExistException.testSpecAlreadyExists(); });
        TestDefinition testSpec = testDefinitionRepository.save(TestDefinition.of(dto.getName()));
        return testRunMapper.map(testSpec);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestSpecDto> getTests() { //TestRunDto
        return testDefinitionRepository.findAll().stream()
                .map(TestRunMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TestSpecDto updateTestStatus(final TestSpecDto dto) {
        final TestDefinition testSpec = testDefinitionRepository.findById(dto.getId())
                .orElseThrow(() -> { throw AlreadyExistException.testSpecNotFound(); });
        testSpec.setStatus(dto.getStatus());
        return testRunMapper.map(testSpec);
    }

    @Override
    @Transactional
    public void deleteTestSpecification(final Integer id) {
        final TestDefinition testDefinition = testDefinitionRepository.findById(id)
                .orElseThrow(()-> { throw AlreadyExistException.testSpecNotFound(); });
        testDefinitionRepository.delete(testDefinition);
    }

}
