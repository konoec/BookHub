package konoec.bookhub.domain.userManagement.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
