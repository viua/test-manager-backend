package sb.testmanager.service;

import sb.testmanager.controller.dto.TestSpecDto;

import java.util.List;

public interface TestSpecificationService {

    /**
     * Add new test specification
     * @param dto Test Specification DTO
     * @return created Test Specification DTO
     */
    TestSpecDto addTestSpecification(TestSpecDto dto);

    /**
     * Get all test runs
     * @return list of Test Runs DTO
     */
    List<TestSpecDto> getTests();

    /**
     * Update Test run status
     * @param dto Test Specification DTO
     * @return updated Test Specification DTO
     */
    TestSpecDto updateTestStatus(TestSpecDto dto);

    /**
     * Delete Test Specification
     * @param dto Test Specification DTO to be deleted
     */
    void deleteTestSpecification(Integer dto);
}
