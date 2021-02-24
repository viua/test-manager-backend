package /*com.*/sb.testmanager.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TestDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "testDefSeqGen", sequenceName = "TEST_DEFINITION_SEQ", initialValue = 5, allocationSize = 100)
    @GeneratedValue(generator = "testDefinitionIdSeq")
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @NonNull
    @Column(nullable = false) //TODO Enum
    private String status = "undefined";

    public static TestDefinition of(String name) {
        Objects.requireNonNull(name, "Test specification name cannot be null or blank.");
        return new TestDefinition(name);
    }
}
