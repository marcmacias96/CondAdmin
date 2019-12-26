package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.USer;
import com.mamp.software.condadmin.services.ICondominiumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/condominium")
public class CondominiumController {
	@Autowired
    private ICondominiumService service;

	@Autowired
    private IUser srvUser;

    @GetMapping(value = "/create")
    public String create(Model model){
        Condominium condominium = new Condominium();
        model.addAttribute("condominium", condominium);
        model.addAttribute("title","Registro de nuevo Condominio");

        return "condominium/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Condominium condominium = service.findById(id);
        model.addAttribute("condominium", condominium);
        return "condominium/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        Condominium condominium = service.findById(id);
        model.addAttribute("condominium",condominium);
        return "condominium/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/condominium/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<Condominium> condominiumList = service.findAll();
        model.addAttribute("title","Codominios");
        model.addAttribute("condominiumList", condominiumList);
        return "condominium/list";
    }
    @GetMapping(value = "/myCondo")
    public String listByCondom(Model model, Authentication authentication){
        USer user = srvUser.findByName(authentication.getName());
        Condominium condominium = service.findByUser(user.getIdUser());
        model.addAttribute("title","Codominios");
        model.addAttribute("condominium", condominium);
        return "condominium/card";
    }

    @PostMapping(value = "/save")
    public String save(Condominium condominium, Model model, RedirectAttributes redirectAttributes){
        try {
            service.save(condominium);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            System.out.println(e);
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/condominium/list";
    }
}
