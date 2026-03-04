package ForoHub.Controller;


import ForoHub.Repository.TopicoRepository;
import ForoHub.Domain.topico.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoRepository repository;

    @PostMapping
    @Transactional
    public DatosDetalleTopico registrar(@RequestBody @Valid DatosRegistroTopico datos) {

        if (repository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent()) {
            throw new RuntimeException("Tópico duplicado");
        }

        var topico = new Topico(datos);
        repository.save(topico);

        return new DatosDetalleTopico(topico);
    }

    @GetMapping
    public List<DatosDetalleTopico> listar() {
        return repository.findAll()
                .stream()
                .map(DatosDetalleTopico::new)
                .toList();
    }

    @GetMapping("/{id}")
    public DatosDetalleTopico detallar(@PathVariable Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        return new DatosDetalleTopico(topico);
    }

    @PutMapping("/{id}")
    @Transactional
    public DatosDetalleTopico actualizar(
            @PathVariable Long id,
            @RequestBody DatosActualizarTopico datos) {

        var topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        topico.actualizar(datos);
        return new DatosDetalleTopico(topico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Tópico no encontrado");
        }

        repository.deleteById(id);
    }
}