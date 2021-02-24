package sb.testmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.testmanager.model.TestDefinition;

import java.util.Optional;

@Repository
public interface TestDefinitionRepository extends JpaRepository<TestDefinition, Integer> {
    Optional<TestDefinition> findByName(final String name); //! IgnoreCase
}

// do segregation