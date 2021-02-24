package sb.testmanager.controller.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TestSpecDto {

    private Integer id;

    @NotEmpty
    @NonNull
    private String name;

    private String status; // enum check
}