package sb.testmanager.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.model.TestDefinition;
import sb.testmanager.repository.TestDefinitionRepository;
import sb.testmanager.service.TestSpecificationService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/tm/test")// rename to /tests
public class TestSpecificationController extends BaseController {

    private final TestDefinitionRepository testDefinitionRepository;
    private final TestSpecificationService testSpecificationService;

    @GetMapping
    @ResponseStatus(OK)
    public List<TestDefinition> getTestRuns() {
        log.info("GET: /api/tm/tests" + testDefinitionRepository.findAll().size());
        return testDefinitionRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addTestSpecification(final @RequestBody @Valid TestSpecDto dto) {
        log.info("POST: #{}", dto);
        testSpecificationService.addTestSpecification(dto);
    }

    @PutMapping("/{id}")
    public void updateTestRunStatus(final @PathVariable Integer id,
                                    final @RequestBody @Validated TestSpecDto dto) {
        log.info("PUT: /tests/{}", id); // update status only !! no unused fields in dto/wrapped enum in body only
        TestDefinition testDefinition = testDefinitionRepository.findById(id).orElseThrow();
        testDefinition.setStatus(dto.getStatus());
        testDefinitionRepository.save(testDefinition); //find update instead of save??
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTestSpecification(final @PathVariable @NonNull /* ?? is that actually work? */ Integer id) {
        log.info("DELETE: /tests/{}", id);
        TestDefinition testDefinition = testDefinitionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("cannot find test definition with ID: #" + id));
        testDefinitionRepository.delete(testDefinition);
    }

}
