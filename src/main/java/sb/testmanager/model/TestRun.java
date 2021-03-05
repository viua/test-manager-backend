package sb.testmanager.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "TEST_RUNS")
public class TestRun {

    private static final long serialVersionUID = 1L;

    private static final String CREATION_TIME_COLUMN = "creation_time";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = CREATION_TIME_COLUMN, nullable = false)
    private LocalDateTime creationTime;

}
