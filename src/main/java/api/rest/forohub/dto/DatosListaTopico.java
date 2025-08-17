package api.rest.forohub.dto;

import api.rest.forohub.model.Topico;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        String cursoNombre,
        String autorNombre,
        Boolean status

) {

    public DatosListaTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso().getNombre(),
                topico.getAutor().getNombre(),
                topico.getStatus()
        );
    }
}
