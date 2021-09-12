package app.handler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class ValidationError {

    private static final String MESSAGE = "Validations errors";

    private final String message;

    private final List<FieldMessageError> fieldMessageErrors;

    public static ValidationError of(final List<FieldError> fieldErrors) {

        final var fieldMessageErrors = fieldErrors
                .stream()
                .map(FieldMessageError::of)
                .collect(Collectors.toList());

        return ValidationError
                .builder()
                .message(MESSAGE)
                .fieldMessageErrors(fieldMessageErrors).build();
    }

}
