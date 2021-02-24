package sb.testmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.controller.exceptions.AlreadyExistException;
import sb.testmanager.service.TestSpecificationService;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestSpecificationControllerTest {

    private MockMvc mockMvc;
    private static final String TEST_SPEC_URL = "/api/tm/test";

    @Mock
    private TestSpecificationService testSpecificationService;

    @InjectMocks
    private TestSpecificationController testSpecificationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(testSpecificationController).build();
    }

    @Test
    public void shouldGetAllTestSpecs() throws Exception {
        // given
        when(testSpecificationService.getTests())
                .thenReturn(asList(new TestSpecDto(0, "Test Case 1", "undefined")));

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get(TEST_SPEC_URL)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Test Case 1"));
    }

    @Test
    public void shouldAddTestSpec() throws Exception {
        // given
        final TestSpecDto dto = new TestSpecDto("Test Case 1");

        // when
        mockMvc.perform(MockMvcRequestBuilders.post(TEST_SPEC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isCreated());

        // then
        verify(testSpecificationService).addTestSpecification(isA(TestSpecDto.class));
        verify(testSpecificationService).addTestSpecification(eq(dto));
    }

    @Test
    public void shouldThrowExceptionIfTestSpecNameMissing() throws Exception {
        // given
        final TestSpecDto dto = new TestSpecDto("");

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post(TEST_SPEC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldThrowExceptionIfTestSpecNameDuplicated() throws Exception {
        final TestSpecDto dto = new TestSpecDto("Test Case 1");
        given(testSpecificationService.addTestSpecification(any(TestSpecDto.class)))
                .willThrow(AlreadyExistException.testSpecAlreadyExists());

        mockMvc.perform(MockMvcRequestBuilders.post(TEST_SPEC_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dto))).andDo(print())
                .andExpect(status().isConflict());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
