package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.ICondominium;
import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.*;
import com.mamp.software.condadmin.services.ICondominiumService;
import com.mamp.software.condadmin.services.IIncomeService;
import com.mamp.software.condadmin.services.IOwnerService;
import com.mamp.software.condadmin.services.IHouseService;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/house")
public class HouseController {
	@Autowired
    private IHouseService srvHouse;
	
	@Autowired
	private IOwnerService srvOwner;

    @Autowired
    private ICondominiumService srvCondm;

    @Autowired
    private IUser srvUser;

    @Autowired
    private IIncomeService srvIncome;

    @GetMapping(value = "/create/{id}")
    public String create(@PathVariable(value = "id") Integer id, Model model){
        House house = new House();
        house.setOwnerId(id);
        model.addAttribute("house", house);
        model.addAttribute("title","Registro de nueva Casa");
        return "house/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        House house = srvHouse.findById(id);
        List<Income> incomeList = srvIncome.findByHouse(house.getIdhouse());
        model.addAttribute("house", house);
        model.addAttribute("incomeList", incomeList);
        return "house/card";
    }


    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        House house = srvHouse.findById(id);
        house.setOwnerId(house.getOwner().getIdowner());
        model.addAttribute("house",house);
        return "house/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            srvHouse.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/house/list";
    }

    @GetMapping(value = "/listByOwner/{id}")
    public String listByOwner(@PathVariable(value = "id") Integer id,Model model){
        List<House> houseList = srvHouse.findByOwner(id);
        model.addAttribute("title","Listado de Casas");
        model.addAttribute("houseList", houseList);
        return "house/list";
    }

    @GetMapping(value = "/listByCondom/{id}")
    public String lisByCondom(@PathVariable(value = "id") Integer id,Model model){
        List<House> houseList = srvHouse.findByCondom(id);
        model.addAttribute("title","Listado de Casas");
        model.addAttribute("houseList", houseList);
        return "house/list";
    }

    @PostMapping(value = "/save")
    public String save(@Valid  House house, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes, Authentication authentication){
        Condominium condominium = null;
        Owner owner= null;
        try {
            if (bindingResult.hasErrors()){
                model.addAttribute("title","Error al guardar");
                return "house/form";
            }
            owner = srvOwner.findById(house.getOwnerId());
            USer user = srvUser.findByName(authentication.getName());
            condominium = srvCondm.findByUser(user.getIdUser());
            house.setCondominium(condominium);
            house.setOwner(owner);
            srvHouse.save(house);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/owner/retrive/" + owner.getIdowner() ;
    }
}
