package api.rest.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje
) {}
