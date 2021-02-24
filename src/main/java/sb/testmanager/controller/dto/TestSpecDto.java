package sb.testmanager.controller.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class TestSpecDto {

    // private Integer id;

    //@NotBlank
    @NotEmpty
    @NonNull
    private String name;

    private String status; // enum check in existing project
}