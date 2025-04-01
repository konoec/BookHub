package konoec.bookhub.controller.userManagement;

import konoec.bookhub.domain.userManagement.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
}
