package api.rest.forohub.controller;

import api.rest.forohub.dto.*;
import api.rest.forohub.model.Topico;
import api.rest.forohub.service.TopicoService;
import jakarta.validation.Valid;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {
    private final TopicoService topicoService;
    private final ValidatorFactory validatorFactory;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopicoDTO> registrarTopico(@RequestBody @Valid CrearTopicoDTO datos,
                                                                 UriComponentsBuilder uriComponentsBuilder){
        // Llamamos al servicio y recibimos de vuelta el DTO de respuesta ya listo.
        DatosRespuestaTopicoDTO datosRespuesta = topicoService.crear(datos);

        // Construimos la URL del nuevo recurso creado.
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosRespuesta.id()).toUri();

        // Retornamos el código 201 Created, la URL y el DTO de respuesta en el body.
        return ResponseEntity.created(url).body(datosRespuesta);

    }

    @GetMapping
    public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault(size = 10, sort ={"titulo"}) Pageable pageable){
        var page = topicoService.listarTopicos(pageable);
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity unTopico(@PathVariable Long id){
        var unTopico = topicoService.unTopico(id);
        return ResponseEntity.ok(new DatosDetallesTopicos(unTopico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosDetallesTopicos> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico dto){
        Topico topicoActualizado = topicoService.actualizarTopico(id, dto);
        return ResponseEntity.ok(new DatosDetallesTopicos(topicoActualizado));
    }
}
