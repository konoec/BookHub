package konoec.bookhub.controller.userManagement;

import konoec.bookhub.domain.userManagement.permission.Permission;
import konoec.bookhub.domain.userManagement.permission.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public String listPermissions(Model model) {
        model.addAttribute("permissions", permissionService.findAll());
        return "userManagement/permissions/list";
    }

    @GetMapping("/{id}")
    public String getPermission(@PathVariable Long id, Model model) {
        model.addAttribute("permission", permissionService.findById(id));
        return "userManagement/permissions/detail";
    }

    @GetMapping("/new")
    public String newPermissionForm(Model model) {
        model.addAttribute("permission", new Permission());
        return "userManagement/permissions/form";
    }

    @PostMapping
    public String savePermission(@Valid @ModelAttribute Permission permission,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "userManagement/permissions/form";
        }
        permissionService.save(permission);
        return "redirect:/permissions";
    }

    @GetMapping("/edit/{id}")
    public String editPermissionForm(@PathVariable Long id, Model model) {
        model.addAttribute("permission", permissionService.findById(id));
        return "userManagement/permissions/form";
    }

    @PostMapping("/update/{id}")
    public String updatePermission(@PathVariable Long id,
                                 @Valid @ModelAttribute Permission permission,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "userManagement/permissions/form";
        }
        permissionService.update(id, permission);
        return "redirect:/permissions";
    }

    @GetMapping("/delete/{id}")
    public String deletePermission(@PathVariable Long id) {
        permissionService.delete(id);
        return "redirect:/permissions";
    }

    @GetMapping("/activate/{id}")
    public String activatePermission(@PathVariable Long id) {
        permissionService.activate(id);
        return "redirect:/permissions";
    }
}