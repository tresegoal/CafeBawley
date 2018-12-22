/*
package cm.rst.controller;

import cm.rst.entities.Role;
import cm.rst.service.ICrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

*/
/**
 * @author Fabrice
 *//*

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private ICrudService<Role, Long> roleService;

    @RequestMapping(value={"","/"})
    public String getAllRole(Model model) {
        model.addAttribute("roles", roleService.getAll());
        return "roles";
    }
	
    @RequestMapping(value="/nouveau", method = RequestMethod.GET)
    public String createRole(Model model) {
        model.addAttribute("role", new Role());
        return "roleForm";
    }

    @RequestMapping(value="/enregistrer", method = RequestMethod.POST)
    public String saveRole(Role role) {

        if(role.getRoleId() != null) {
            roleService.update(role);
        }else{
            roleService.add(role);
        }

        return "redirect:/roles";
    }

    @RequestMapping(value="/editer/{id}", method = RequestMethod.GET)
    public String editRole(Model model, @PathVariable(value = "id") Long id) {

        if(id != null) {
            Role role = roleService.findE(id);
            if(role != null) {
                model.addAttribute("role", roleService.findE(id));
            }
        }

        return "roleForm";
    }

    @RequestMapping(value="/supprimer/{id}", method = RequestMethod.GET)
    public String deleteRole(@PathVariable Long id) {

        if(id != null) {
            Role role = roleService.findE(id);
            if(role != null) {
                roleService.delete(id);
            }
        }

        return "redirect:/roles";
    }
}
*/
