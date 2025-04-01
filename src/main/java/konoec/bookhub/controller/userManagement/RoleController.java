package konoec.bookhub.controller.userManagement;

import konoec.bookhub.domain.userManagement.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
}
