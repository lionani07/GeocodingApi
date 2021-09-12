package app.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

@AllArgsConstructor
@Getter
public class FieldMessageError {

    private final String field;

    private final String message;

    public static FieldMessageError of(final FieldError fieldError) {
        return new FieldMessageError(fieldError.getField(), fieldError.getDefaultMessage());
    }

}
