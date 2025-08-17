package api.rest.forohub.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "usuarios_perfiles", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "usuario_id"), // Clave foránea de esta entidad (Usuario)
            inverseJoinColumns = @JoinColumn(name = "perfil_id") // Clave foránea de la otra entidad (Perfil)
    )
    private Set<Perfil> perfiles = new HashSet<>();


    // Un usuario puede tener muchos tópicos. "autor" es el campo en la clase Topico.
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Topico> topicos = new ArrayList<>();


    // Un usuario puede tener muchas respuestas. "autor" es el campo en la clase Respuesta.
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
