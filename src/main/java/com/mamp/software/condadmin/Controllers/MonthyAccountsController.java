package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;
import com.mamp.software.condadmin.services.IMonthlyAccountsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/monthyAccounts")
public class MonthyAccountsController {
    public IMonthlyAccountsService service;

    @GetMapping(value = "/create")
    public String create(Model model){
        MonthlyAccounts monthlyAccounts = new MonthlyAccounts();
        model.addAttribute("annualCounts", monthlyAccounts);
        model.addAttribute("title","Registro nuevo de Cuentas Mensuales");

        return "monthyAccounts/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        MonthlyAccounts monthlyAccounts = service.findById(id);
        model.addAttribute("monthlyAccounts", monthlyAccounts);
        return "monthyAccounts/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        MonthlyAccounts monthlyAccounts = service.findById(id);
        model.addAttribute("annualCounts",monthlyAccounts);
        return "monthyAccounts/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/monthyAccounts/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<MonthlyAccounts> monthlyAccountsList = service.findAll();
        model.addAttribute("monthlyAccountsList", monthlyAccountsList);
        return "monthyAccounts/list";
    }

    @PostMapping(value = "/save")
    public String save(MonthlyAccounts monthlyAccounts, Model model, RedirectAttributes redirectAttributes){
        try {
            service.save(monthlyAccounts);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/monthyAccounts/list";
    }
}
