package /*com.*/sb.testmanager.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Setter
@Getter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TestSpecification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "testSpecSeqGen", sequenceName = "TEST_SPECIFICATION_SEQ", initialValue = 5, allocationSize = 100)
    @GeneratedValue(generator = "testSpecificationIdSeq")
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @NonNull
    @Column(nullable = false) //TODO REMOVE
    private String status = "UNDEFINED";


    @OneToMany(mappedBy = "testSpecification", cascade = CascadeType.ALL)
    private List<TestRunExecutionStatus> testRunExecutionStatuses = new ArrayList<>();

    public static TestSpecification of(String name) {
        Objects.requireNonNull(name, "Test specification name cannot be empty or null.");
        return new TestSpecification(name);
    }
}
