package sb.testmanager.service.mapper;

import org.springframework.stereotype.Component;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.model.RunStatus;
import sb.testmanager.model.TestSpecification;
import sb.testmanager.model.TestRunExecutionStatus;

import java.util.Comparator;

import static java.util.Arrays.asList;

@Component
public class TestRunMapper {

    public static TestSpecDto map(TestSpecification testSpecification) {
        TestRunExecutionStatus latestTestRunExecutionStatus = getLatestRunOrDefault(testSpecification);
        return new TestSpecDto(testSpecification.getId(),
                testSpecification.getName(),
                latestTestRunExecutionStatus.getRunStatus().name()
        );
    }

    public static TestRunExecutionStatus getLatestRunOrDefault(TestSpecification definition) {
        return definition.getTestRunExecutionStatuses().stream()
                .max(Comparator.comparing(TestRunExecutionStatus::getCreationTime))
                .orElseGet(() -> {
                    TestRunExecutionStatus run = new TestRunExecutionStatus(RunStatus.UNDEFINED, definition);
                    definition.setTestRunExecutionStatuses(asList(run));
                    return run;
                });
    }

}
