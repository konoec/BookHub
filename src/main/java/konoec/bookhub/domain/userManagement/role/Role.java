package konoec.bookhub.domain.userManagement.role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import konoec.bookhub.domain.userManagement.permission.Permission;
import konoec.bookhub.domain.userManagement.user.User;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre del rol debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[A-Z_]+$", message = "El nombre del rol debe estar en mayúsculas y puede contener guiones bajos")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @NotEmpty(message = "El rol debe tener al menos un permiso")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "role_permissions",
        joinColumns = @JoinColumn(name = "role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;
}