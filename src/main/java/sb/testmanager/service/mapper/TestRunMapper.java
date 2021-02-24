package sb.testmanager.service.mapper;

import org.springframework.stereotype.Component;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.model.TestDefinition;

@Component
public class TestRunMapper {
    public static TestSpecDto map(TestDefinition testDefinition) {
        return new TestSpecDto(testDefinition.getId(), testDefinition.getName(), testDefinition.getStatus());
    }
}
