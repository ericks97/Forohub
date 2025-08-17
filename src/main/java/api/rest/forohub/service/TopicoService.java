package api.rest.forohub.service;

import api.rest.forohub.dto.*;
import api.rest.forohub.infra.exceptions.ValidacionDeIntegridadException;
import api.rest.forohub.model.Curso;
import api.rest.forohub.model.Topico;
import api.rest.forohub.model.Usuario;
import api.rest.forohub.repository.CursoRepository;
import api.rest.forohub.repository.TopicoRepository;
import api.rest.forohub.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public DatosRespuestaTopicoDTO crear(CrearTopicoDTO entidad) {
        // REGLA DE NEGOCIO: No duplicados
        if (topicoRepository.existsByTituloAndMensaje(entidad.titulo(), entidad.mensaje())) {
            throw new ValidacionDeIntegridadException("Ya existe un tópico con el mismo título y mensaje.");
        }

       // BÚSQUEDA DE ENTIDADES
        Usuario autor = usuarioRepository.findById(entidad.autorId())
                .orElseThrow(() -> new ValidacionDeIntegridadException("Autor no encontrado."));
        Curso curso = cursoRepository.findById(entidad.cursoId())
                .orElseThrow(() -> new ValidacionDeIntegridadException("Curso no encontrado."));


        // CREACIÓN Y PERSISTENCIA
        Topico topico = new Topico(entidad, autor, curso);
        topicoRepository.save(topico);

        // RESPUESTA
        return new DatosRespuestaTopicoDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }

    public Page<DatosListaTopico> listarTopicos(Pageable pageable) {
        return topicoRepository.findAllByStatusTrue(pageable).map(DatosListaTopico::new);
    }

    @Transactional
    public  void eliminarTopico(Long id) {

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Topico no encontrado."));
        topico.desactivar();
    }


    @Transactional
    public Topico actualizarTopico(Long id, @Valid DatosActualizarTopico dto) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new EntityNotFoundException("Topico no encontrado.");
        }
        Topico topico = topicoOptional.get();

        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());

        return topico;
    }
    public Topico unTopico(Long id) {
        return topicoRepository.findByIdAndStatusTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Topico no encontrado."));
    }
}
