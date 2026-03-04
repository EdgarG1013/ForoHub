package ForoHub.Domain.topico;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    private String autor;
    private String curso;

    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = "ACTIVO";
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizar(DatosActualizarTopico datos) {
        if (datos.titulo() != null)
            this.titulo = datos.titulo();

        if (datos.mensaje() != null)
            this.mensaje = datos.mensaje();
    }
}
