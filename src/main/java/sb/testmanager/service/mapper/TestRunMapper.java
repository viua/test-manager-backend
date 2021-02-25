package sb.testmanager.service.mapper;

import org.springframework.stereotype.Component;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.model.RunStatus;
import sb.testmanager.model.TestSpecification;
import sb.testmanager.model.TestRun;

import java.util.Comparator;

import static java.util.Arrays.asList;

@Component
public class TestRunMapper {

    public static TestSpecDto map(TestSpecification testSpecification) {
        TestRun latestTestRun = getLatestRunOrDefault(testSpecification);
        return new TestSpecDto(testSpecification.getId(),
                testSpecification.getName(),
                latestTestRun.getRunStatus().name()
        );
    }

    public static TestRun getLatestRunOrDefault(TestSpecification definition) {
        return definition.getTestRuns().stream()
                .max(Comparator.comparing(TestRun::getCreationTime))
                .orElseGet(() -> {
                    TestRun run = new TestRun(RunStatus.UNDEFINED, definition);
                    definition.setTestRuns(asList(run));
                    return run;
                });
    }

}
