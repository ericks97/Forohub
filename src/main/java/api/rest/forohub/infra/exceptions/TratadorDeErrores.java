package api.rest.forohub.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    // Para errores de validación (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(err -> "Campo '" + err.getField() + "': " + err.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(ValidacionDeIntegridadException.class)
    public ResponseEntity tratarErrorDeIntegridad(ValidacionDeIntegridadException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> tratarNoEncontrado(EntityNotFoundException e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }
}
