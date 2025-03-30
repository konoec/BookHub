package konoec.bookhub.domain.userManagement.role;

import jakarta.persistence.*;
import konoec.bookhub.domain.userManagement.permission.Permission;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolePermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
}
