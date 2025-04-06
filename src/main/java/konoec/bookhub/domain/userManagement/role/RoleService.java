package konoec.bookhub.domain.userManagement.role;

import konoec.bookhub.domain.userManagement.permission.Permission;
import konoec.bookhub.domain.userManagement.permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findById(Long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Long roleId, Role role) {
        return roleRepository.findById(roleId)
                .map(existingRole -> {
                    existingRole.setName(role.getName());
                    return roleRepository.save(existingRole);
                })
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    public void delete(Long roleId) {
        roleRepository.findById(roleId)
                .map(role -> {
                    role.setIsDeleted(true);
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
    }

    public Role addPermission(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
        role.getPermissions().add(permission);
        return roleRepository.save(role);
    }

    public Role removePermission(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
        role.getPermissions().remove(permission);
        return roleRepository.save(role);
    }

    public List<Role> findByPermissionId(Long permissionId) {
        return roleRepository.findByPermissionId(permissionId);
    }
}
