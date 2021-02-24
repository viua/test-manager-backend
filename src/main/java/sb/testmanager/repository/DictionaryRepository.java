package sb.testmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.testmanager.model.TestRunStatus;

@Repository
public interface DictionaryRepository extends JpaRepository<TestRunStatus, Integer> {
}
