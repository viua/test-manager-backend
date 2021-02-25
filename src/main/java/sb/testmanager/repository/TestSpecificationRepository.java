package sb.testmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.testmanager.model.TestSpecification;

import java.util.Optional;

@Repository
public interface TestSpecificationRepository extends JpaRepository<TestSpecification, Integer> {
    Optional<TestSpecification> findByName(final String name);
}
