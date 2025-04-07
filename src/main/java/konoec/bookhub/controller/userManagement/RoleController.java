package konoec.bookhub.controller.userManagement;

import konoec.bookhub.domain.userManagement.role.Role;
import konoec.bookhub.domain.userManagement.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public String listRoles(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "userManagement/roles/list";
    }

    @GetMapping("/{id}")
    public String getRole(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        return "userManagement/roles/detail";
    }

    @GetMapping("/new")
    public String newRoleForm(Model model) {
        model.addAttribute("role", new Role());
        return "userManagement/roles/form";
    }

    @PostMapping
    public String saveRole(@Valid @ModelAttribute Role role,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "userManagement/roles/form";
        }
        roleService.save(role);
        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String editRoleForm(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleService.findById(id));
        return "userManagement/roles/form";
    }

    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable Long id,
                           @Valid @ModelAttribute Role role,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "userManagement/roles/form";
        }
        roleService.update(id, role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return "redirect:/roles";
    }

    @GetMapping("/activate/{id}")
    public String activateRole(@PathVariable Long id) {
        roleService.activate(id);
        return "redirect:/roles";
    }

    @GetMapping("/permission/{permissionId}")
    public String getRolesByPermission(@PathVariable Long permissionId, Model model) {
        model.addAttribute("roles", roleService.findByPermissionId(permissionId));
        return "userManagement/roles/list";
    }

    @PostMapping("/{roleId}/permissions/add/{permissionId}")
    public String addPermissionToRole(@PathVariable Long roleId,
                                    @PathVariable Long permissionId) {
        roleService.addPermission(roleId, permissionId);
        return "redirect:/roles/" + roleId;
    }

    @PostMapping("/{roleId}/permissions/remove/{permissionId}")
    public String removePermissionFromRole(@PathVariable Long roleId,
                                         @PathVariable Long permissionId) {
        roleService.removePermission(roleId, permissionId);
        return "redirect:/roles/" + roleId;
    }
}