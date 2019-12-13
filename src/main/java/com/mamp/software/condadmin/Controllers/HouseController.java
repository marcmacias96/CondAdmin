package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.House;
import com.mamp.software.condadmin.Models.entities.Owner;
import com.mamp.software.condadmin.services.IOwnerService;
import com.mamp.software.condadmin.services.IHouseService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/house")
public class HouseController {
	@Autowired
    public IHouseService service;
	
	@Autowired
	public IOwnerService srvOwner;

    @GetMapping(value = "/create")
    public String create(Model model){
        House house = new House();
        model.addAttribute("house", house);
        model.addAttribute("title","Registro de nueva Casa");
        List<Owner> owner = srvOwner.findAll();
        model.addAttribute("owner", owner);

        return "house/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        House house = service.findById(id);
        model.addAttribute("house", house);
        return "house/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        House house = service.findById(id);
        model.addAttribute("house",house);
        List<Owner> owner = srvOwner.findAll();
        model.addAttribute("owner", owner);
        return "house/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/house/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<House> houseList = service.findAll();
        model.addAttribute("title","Listado de Casas");
        model.addAttribute("houseList", houseList);
        return "house/list";
    }

    @PostMapping(value = "/save")
    public String save(@Valid  House house, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        try {
            if (bindingResult.hasErrors()){
                model.addAttribute("title","Error al guardar");
                List<Owner> owner = srvOwner.findAll();
                model.addAttribute("owner", owner);
                return "house/form";
            }
            service.save(house);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/house/list";
    }
}
