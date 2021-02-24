package sb.testmanager.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.model.TestDefinition;
import sb.testmanager.repository.TestDefinitionRepository;
import sb.testmanager.service.impl.TestSpecificationServiceImpl;
import sb.testmanager.service.mapper.TestRunMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TestSpecificationServiceTest {

    @Spy
    private TestRunMapper testRunMapper;

    @Mock
    private TestDefinitionRepository testDefinitionRepository;

    @InjectMocks
    private TestSpecificationServiceImpl testSpecificationService;

    @Test
    void shouldReturnAllTest() {
        // given
        TestDefinition test1  = TestDefinition.of("Test 1");
        TestDefinition test2  = TestDefinition.of("Test 2");

        given(testDefinitionRepository.findAll()).willReturn(List.of(test1, test2));

        // when
        List<TestSpecDto> specsActual = testSpecificationService.getTests();

        // then
        verify(testDefinitionRepository).findAll();
        assertThat(specsActual)
                .extracting(TestSpecDto::getName, TestSpecDto::getStatus)
                .containsOnly(tuple(test1.getName(), test1.getStatus()),
                        tuple(test2.getName(), test2.getStatus()));
    }

    @Test
    void shouldUpdateTestStatus() {
        // given
        TestDefinition testDefinition = TestDefinition.of("Test 1");
        testDefinition.setId(0);
        testDefinition.setStatus("unspecified");

        given(testDefinitionRepository.findById(any(Integer.class)))
                .willReturn(Optional.of(testDefinition));

        // when
        TestSpecDto expected = new TestSpecDto(testDefinition.getId(), testDefinition.getName(), "passed");
        TestSpecDto actual = testSpecificationService.updateTestStatus(expected);

        // then
        assertEquals(actual, expected);
    }
}