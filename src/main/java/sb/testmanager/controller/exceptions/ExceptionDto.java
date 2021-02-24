package sb.testmanager.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @toString lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {

    private String defaultMessage;

    @Override
    public String toString() {
        return "ExceptionDto{"
                + ", defaultMessage=" + defaultMessage
                + '}';
    }
}
