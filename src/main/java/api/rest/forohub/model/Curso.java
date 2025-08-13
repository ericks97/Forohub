package api.rest.forohub.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name="cursos")
@Entity(name="Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;

}
