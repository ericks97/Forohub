package api.rest.forohub.repository;

import api.rest.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
    Page<Topico> findAllByStatusTrue(Pageable pageable);

    Optional<Topico>findByIdAndStatusTrue(Long id);
}
