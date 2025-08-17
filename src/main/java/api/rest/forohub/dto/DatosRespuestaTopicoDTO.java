package api.rest.forohub.dto;

import api.rest.forohub.model.Topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String nombreAutor,
        String nombreCurso
){

}
