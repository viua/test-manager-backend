package sb.testmanager.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "TEST_RUN_STATUS_DICT")
@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TestRunStatus implements Serializable {

    @Id
    @NonNull
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String value;

    @NonNull
    @Column(nullable = false, unique = true)
    private String caption;

}
