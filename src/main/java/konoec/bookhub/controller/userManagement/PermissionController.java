package konoec.bookhub.controller.userManagement;

import konoec.bookhub.domain.userManagement.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;
}
