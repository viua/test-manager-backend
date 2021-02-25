package sb.testmanager.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.model.TestSpecification;
import sb.testmanager.repository.TestSpecificationRepository;
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
    private TestSpecificationRepository testSpecificationRepository;

    @InjectMocks
    private TestSpecificationServiceImpl testSpecificationService;

    @Test @Disabled
    void shouldAddTestSpecification() {
        // not implemented
    }

    @Test
    void shouldReturnAllTest() {
        // given
        TestSpecification test1  = TestSpecification.of("Test 1");
        TestSpecification test2  = TestSpecification.of("Test 2");

        given(testSpecificationRepository.findAll()).willReturn(List.of(test1, test2));

        // when
        List<TestSpecDto> specsActual = testSpecificationService.getTestsRun();

        // then
        verify(testSpecificationRepository).findAll();
        assertThat(specsActual)
                .extracting(TestSpecDto::getName, TestSpecDto::getStatus)
                .containsOnly(tuple(test1.getName(), test1.getStatus()),
                        tuple(test2.getName(), test2.getStatus()));
    }

    @Test
    void shouldUpdateTestStatus() {
        // given
        TestSpecification testSpecification = TestSpecification.of("Test 1");
        testSpecification.setId(0);
        testSpecification.setStatus("UNSPECIFIED");

        given(testSpecificationRepository.findById(any(Integer.class)))
                .willReturn(Optional.of(testSpecification));

        // when
        TestSpecDto expected = new TestSpecDto(testSpecification.getId(), testSpecification.getName(), "PASSED");
        TestSpecDto actual = testSpecificationService.updateTestStatus(expected);

        // then
        assertEquals(actual, expected);
    }
}