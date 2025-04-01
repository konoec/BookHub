package konoec.bookhub.domain.userManagement.permission;

import jakarta.persistence.*;
import konoec.bookhub.domain.userManagement.role.Role;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}
