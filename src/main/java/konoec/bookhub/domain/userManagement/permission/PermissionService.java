package konoec.bookhub.domain.userManagement.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }
}
