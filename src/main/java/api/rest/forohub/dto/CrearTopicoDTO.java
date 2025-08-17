package api.rest.forohub.dto;

import api.rest.forohub.model.Curso;
import api.rest.forohub.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearTopicoDTO(
        @NotBlank(message = "El título es obligatorio")
        String titulo,
        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,
        @NotNull(message = "El ID del autor es obligatorio")
        Long autorId,
        @NotNull(message = "El ID del curso es obligatorio")
        Long cursoId
) {
}
