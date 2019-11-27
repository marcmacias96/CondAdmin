package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.Expenses;
import com.mamp.software.condadmin.Models.entities.Income;
import com.mamp.software.condadmin.services.IIncomeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/incomes")
public class IncomesController {
    public IIncomeService service;

    @GetMapping(value = "/create")
    public String create(Model model){
        Income income = new Income();
        model.addAttribute("income", income);
        model.addAttribute("title","Registro de nuevo Ingreso");

        return "incomes/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Income income = service.findById(id);
        model.addAttribute("income", income);
        return "incomes/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        Income income = service.findById(id);
        model.addAttribute("income",income);
        return "incomes/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/incomes/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<Income> incomeListList = service.findAll();
        model.addAttribute("incomeListList", incomeListList);
        return "incomes/list";
    }

    @PostMapping(value = "/save")
    public String save(Income income, Model model, RedirectAttributes redirectAttributes){
        try {
            service.save(income);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/incomes/list";
    }
}
