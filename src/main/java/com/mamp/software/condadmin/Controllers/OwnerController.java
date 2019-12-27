package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.Owner;
import com.mamp.software.condadmin.Models.entities.USer;
import com.mamp.software.condadmin.services.ICondominiumService;
import com.mamp.software.condadmin.services.IOwnerService;

import com.mamp.software.condadmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping(value = "/owner")
public class OwnerController {
	@Autowired
    private IOwnerService srvOwner;

    @Autowired
    private ICondominiumService srvCondom;

    @Autowired
    private IUser srvUser;

    @GetMapping(value = "/create/{id}")
    public String create(@PathVariable(value = "id") Integer id, Model model){
        Owner owner = new Owner();
        owner.setCondmId(id);
        model.addAttribute("owner", owner);
        model.addAttribute("title","Registro de nuevo propietario");

        return "owners/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Owner owner = srvOwner.findById(id);
        model.addAttribute("owner", owner);
        model.addAttribute("title","Actualizacion de registro de nuevo propietario");
        return "owners/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        Owner owner = srvOwner.findById(id);
        model.addAttribute("title","Actualizacion de registro de nuevo propietario");
        model.addAttribute("owner",owner);
        return "owners/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
    	 model.addAttribute("title","eliminacion de registro de nuevo propietario");
        try {
            srvOwner.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/owners/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        model.addAttribute("title","Listado de Propietarios");
        return "owners/list";
    }

    @GetMapping(value = "/listByCondom")
    public String listByCondom( Model model, Authentication authentication){
        USer user = srvUser.findByName(authentication.getName());
        model.addAttribute("user", user);
        model.addAttribute("title", "Listado de Propietarios");
        return "owners/list";
    }

    @PostMapping(value = "/save")
    public String save(@Valid Owner owner, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        Condominium condominium= null;
        model.addAttribute("title","Guardar");
        try {
            if (bindingResult.hasErrors()){
                model.addAttribute("title","Error al guardar");
                return "owners/form";
            }
            condominium = srvCondom.findById(owner.getCondmId());
            owner.setCondominium(condominium);
            srvOwner.save(owner);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/owner/listByCondom";
    }
}
