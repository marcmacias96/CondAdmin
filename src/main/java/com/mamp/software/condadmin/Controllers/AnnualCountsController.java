package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.services.IAnnualCountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/annualCounts")
public class AnnualCountsController {
	@Autowired
    public IAnnualCountsService service;

    @GetMapping(value = "/create")
    public String create(Model model){
    	
        AnnualCounts annualCounts = new AnnualCounts();
        model.addAttribute("annualCounts", annualCounts);
        model.addAttribute("title","Registro nuevo de Cuentas Anuales");
        return "annualCounts/form";
    }

    @GetMapping(value = "/retrieve/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        AnnualCounts annualCounts = service.findById(id);
        model.addAttribute("annualCounts", annualCounts);
        return "annualCounts/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        AnnualCounts annualCounts = service.findById(id);
        model.addAttribute("annualCounts",annualCounts);
        return "annualCounts/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/annualCounts/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<AnnualCounts> annualCountsList = service.findAll();
        model.addAttribute("annualCountsList", annualCountsList);
        return "annualCounts/list";
    }

    @PostMapping(value = "/save")
    public String save(AnnualCounts annualCounts, Model model, RedirectAttributes redirectAttributes){
        try {
            service.save(annualCounts);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/annualCounts/list";
    }
}
