package sb.testmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.controller.exceptions.AlreadyExistException;
import sb.testmanager.model.RunStatus;
import sb.testmanager.model.TestSpecification;
import sb.testmanager.model.TestRunExecutionStatus;
import sb.testmanager.repository.TestSpecificationRepository;
import sb.testmanager.service.TestSpecificationService;
import sb.testmanager.service.mapper.TestRunMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestSpecificationServiceImpl
        implements TestSpecificationService {

    private final TestRunMapper mapper;
    private final TestSpecificationRepository testSpecificationRepository;

    @Override
    @Transactional
    public TestSpecDto addTestSpecification(TestSpecDto dto) {
        testSpecificationRepository.findByName(dto.getName())
                .ifPresent(e -> { throw AlreadyExistException.testSpecAlreadyExists(); });
        TestSpecification testSpec = testSpecificationRepository.save(TestSpecification.of(dto.getName()));
        return mapper.map(testSpec);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TestSpecDto> getTestsRun() {
        return testSpecificationRepository.findAll().stream()
                .map(TestRunMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TestSpecDto updateTestStatus(final TestSpecDto dto) {
        TestSpecification testSpec = testSpecificationRepository.findById(dto.getId())
                .orElseThrow(() -> { throw AlreadyExistException.testSpecNotFound(); });
        TestRunExecutionStatus testRunExecutionStatus = mapper.getLatestRunOrDefault(testSpec);
        testRunExecutionStatus.setRunStatus(RunStatus.valueOf(dto.getStatus())); //TODO: add converter
        return mapper.map(testSpec);
    }

    @Override
    @Transactional
    public void deleteTestSpecification(final Integer id) {
        final TestSpecification testSpecification = testSpecificationRepository.findById(id)
                .orElseThrow(() -> { throw AlreadyExistException.testSpecNotFound(); });
        testSpecificationRepository.delete(testSpecification);
    }

}
