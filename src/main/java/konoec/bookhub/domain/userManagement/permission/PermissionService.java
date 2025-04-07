package konoec.bookhub.domain.userManagement.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public Permission findById(Long id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
    }

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission update(Long id, Permission permission) {
        return permissionRepository.findById(id)
                .map(existingPermission -> {
                    existingPermission.setName(permission.getName());
                    return permissionRepository.save(existingPermission);
                })
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
    }

    public void delete(Long id) {
        permissionRepository.findById(id)
                .map(permission -> {
                    permission.setIsDeleted(true);
                    return permissionRepository.save(permission);
                })
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
    }

    public void activate(Long id) {
        permissionRepository.findById(id)
                .map(permission -> {
                    permission.setIsDeleted(false);
                    return permissionRepository.save(permission);
                })
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));
    }
}
