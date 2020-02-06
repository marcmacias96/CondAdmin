package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.Owner;
import com.mamp.software.condadmin.Models.entities.Role;
import com.mamp.software.condadmin.Models.entities.USer;
import com.mamp.software.condadmin.services.ICondominiumService;
import com.mamp.software.condadmin.services.IOwnerService;

import com.mamp.software.condadmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private ICondominiumService srvCond;

    @Autowired
    private ICondominiumService srvCondom;

    @Autowired
    private IUser srvUser;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value = "/create")
    public String create( Model model){
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        model.addAttribute("title","Registro de nuevo propietario");
        return "owners/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash){
        try{
            Owner owner = srvOwner.findById(id);
            model.addAttribute("owner", owner);
        }catch (Exception e){
            flash.addAttribute("error","Ocurrio un error inesperado");
            return "redirect:/";
        }
        return "owners/card";
    }

    @GetMapping(value = "/myHouse")
    public String myHouse(Model model, Authentication authentication,RedirectAttributes flash){
        try{
            USer user = srvUser.findByName(authentication.getName());
            Owner owner = srvOwner.findByUser(user.getIdUser());
            model.addAttribute("owner", owner);
        }catch (Exception e){
            flash.addAttribute("error","Ocurrio un error inesperado");
            return "redirect:/";
        }
        return "owners/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash){
        try{
            Owner owner = srvOwner.findById(id);
            model.addAttribute("title","Actualizacion de registro de nuevo propietario");
            model.addAttribute("owner",owner);
        }catch (Exception e){
            flash.addAttribute("error","Ocurrio un error iniesperado");
            return "redirect:/retrive/"+id;
        }
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
    public String listByCondom( Model model, Authentication authentication, RedirectAttributes flash){
        try {
            USer user = srvUser.findByName(authentication.getName());
            Condominium condominium = srvCond.findByUser(user.getIdUser());
            model.addAttribute("condominium", condominium);
            model.addAttribute("title", "Listado de Propietarios");
        }catch (Exception e){
            flash.addAttribute("error","Ocurrio un error inesperado");
            return "redirect:/";
        }
        return "owners/list";
    }


    @PostMapping(value = "/save")
    public String save(@Valid Owner owner, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Authentication authentication){
        USer adminUser = srvUser.findByName(authentication.getName());
        Condominium condominium= null;
        model.addAttribute("title","Guardar");
        try {
            if (bindingResult.hasErrors()){
                model.addAttribute("title","Error al guardar");
                return "owners/form";
            }
            USer uSer = new USer();
            uSer.setName(owner.getTuser().getName());
            uSer.setPassword(passwordEncoder.encode("123456"));
            uSer.getRoleList().add(new Role("ROLE_USER"));
            userService.save(uSer);
            uSer = srvUser.findByName(uSer.getName());
            owner.setuSer(uSer);
            condominium = srvCondom.findByUser(adminUser.getIdUser());
            owner.setCondominium(condominium);
            srvOwner.save(owner);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
            return "owner/form";
        }
        return "redirect:/owner/retrive/"+ owner.getIdowner();
    }
}
