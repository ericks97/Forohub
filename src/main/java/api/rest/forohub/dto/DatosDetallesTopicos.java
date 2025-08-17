package api.rest.forohub.dto;

import api.rest.forohub.model.Topico;

public record DatosDetallesTopicos (
    Long id,
    String titulo,
    String mensaje,
    String autorNombre,
    String autorCurso
        )
{
    public DatosDetallesTopicos(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}

