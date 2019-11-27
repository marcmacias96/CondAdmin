package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.Owner;
import com.mamp.software.condadmin.services.IOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/owner")
public class OwnerController {
    public IOwnerService service;

    @GetMapping(value = "/create")
    public String create(Model model){
        OwnerController owner = new OwnerController();
        model.addAttribute("owner", owner);
        model.addAttribute("title","Registro de nuevo paciente");

        return "owner/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Owner owner = service.findById(id);
        model.addAttribute("owner", owner);
        return "owner/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        Owner owner = service.findById(id);
        model.addAttribute("owner",owner);
        return "owner/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/owner/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<Owner> ownerList = service.findAll();
        model.addAttribute("ownerList", ownerList);
        return "owner/list";
    }

    @PostMapping(value = "/save")
    public String save(Owner owner, Model model, RedirectAttributes redirectAttributes){
        try {
            service.save(owner);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/owner/list";
    }
}
