package sb.testmanager.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import sb.testmanager.controller.dto.TestSpecDto;
import sb.testmanager.service.TestSpecificationService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/tm/test")
public class TestManagerController extends BaseController {

    private final TestSpecificationService testSpecificationService;

    @GetMapping
    @ResponseStatus(OK)
    public List<TestSpecDto> getTestRuns() {
        return testSpecificationService.getTestsRun();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void addTestSpecification(final @RequestBody @Valid TestSpecDto dto) {
        log.info("POST /tests with data: {}", dto);
        testSpecificationService.addTestSpecification(dto);
    }

    @PutMapping("/{id}")
    public TestSpecDto updateTestStatus(final @PathVariable Integer id,
                                 final @RequestBody @Valid TestSpecDto dto) {
        log.info("PUT /tests with data: {}", id);
        return testSpecificationService.updateTestStatus(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteTestSpecification(final @PathVariable @NonNull Integer id) {
        log.info("DELETE /tests with ID: #{}", id);
        testSpecificationService.deleteTestSpecification(id);
    }

}
