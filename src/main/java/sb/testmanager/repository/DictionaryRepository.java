package sb.testmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.testmanager.model.TestRunStatusDict;

@Repository
public interface DictionaryRepository extends JpaRepository<TestRunStatusDict, Integer> {
}
