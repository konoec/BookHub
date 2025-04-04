package konoec.bookhub.domain.userManagement.permission;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import konoec.bookhub.domain.userManagement.role.Role;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del permiso es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre del permiso debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[A-Z_]+$", message = "El nombre del permiso debe estar en mayúsculas y puede contener guiones bajos")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}