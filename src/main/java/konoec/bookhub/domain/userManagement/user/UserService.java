package konoec.bookhub.domain.userManagement.user;

import konoec.bookhub.domain.userManagement.role.Role;
import konoec.bookhub.domain.userManagement.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User user) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setPasswordHash(user.getPasswordHash());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setFullName(user.getFullName());
                    existingUser.setRoles(user.getRoles());
                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setIsDeleted(true);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void activate(Long id) {
        userRepository.findById(id)
                .map(user -> {
                    user.setIsDeleted(false);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User addRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        user.getRoles().add(role);
        return userRepository.save(user);
    }

    public User removeRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        user.getRoles().remove(role);
        return userRepository.save(user);
    }

    public List<User> findByRoleId(Long roleId) {
        return userRepository.findByRoleId(roleId);
    }
}
