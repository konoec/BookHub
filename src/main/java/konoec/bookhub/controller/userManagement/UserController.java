package konoec.bookhub.controller.userManagement;

import konoec.bookhub.domain.userManagement.user.User;
import konoec.bookhub.domain.userManagement.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userManagement/users/list";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "userManagement/users/detail";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "userManagement/users/form";
    }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute User user,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "userManagement/users/form";
        }
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "userManagement/users/form";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Long id,
                           @Valid @ModelAttribute User user,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "userManagement/users/form";
        }
        userService.update(id, user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/activate/{id}")
    public String activateUser(@PathVariable Long id) {
        userService.activate(id);
        return "redirect:/users";
    }

    // Gestión de roles
    @GetMapping("/role/{roleId}")
    public String getUsersByRole(@PathVariable Long roleId, Model model) {
        model.addAttribute("users", userService.findByRoleId(roleId));
        return "userManagement/users/list";
    }

    @PostMapping("/{userId}/roles/add/{roleId}")
    public String addRoleToUser(@PathVariable Long userId,
                              @PathVariable Long roleId) {
        userService.addRole(userId, roleId);
        return "redirect:/users/" + userId;
    }

    @PostMapping("/{userId}/roles/remove/{roleId}")
    public String removeRoleFromUser(@PathVariable Long userId,
                                   @PathVariable Long roleId) {
        userService.removeRole(userId, roleId);
        return "redirect:/users/" + userId;
    }
}