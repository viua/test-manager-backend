package sb.testmanager.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "TEST_RUNS")
@EntityListeners(AuditingEntityListener.class)
public class TestRun implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String CREATION_TIME_COLUMN = "creation_time";

    @Id
    @SequenceGenerator(name = "testRunSeqGen", sequenceName = "TEST_RUN_SEQ", initialValue = 5, allocationSize = 100)
    @GeneratedValue(generator = "testRunIdSeq")
    private Integer id;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "RUN_STATUS")
    private RunStatus runStatus;

    @CreatedDate
    @Column(name = CREATION_TIME_COLUMN, nullable = false)
    private LocalDateTime creationTime;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TEST_SPECIFICATION_ID", nullable = false)
    private TestSpecification testSpecification;
}
