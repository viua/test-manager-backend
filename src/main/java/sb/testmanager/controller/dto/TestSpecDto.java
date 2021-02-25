package sb.testmanager.controller.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TestSpecDto {

    private Integer id;

    @NonNull
    @Size(min=1, max=40)
    private String name;

    private String status; // enum check
}