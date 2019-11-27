package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.Expenses;
import com.mamp.software.condadmin.services.IExpensesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/expenses")
public class ExpensesController {
    public IExpensesService service;

    @GetMapping(value = "/create")
    public String create(Model model){
        Expenses expenses = new Expenses();
        model.addAttribute("expenses", expenses);
        model.addAttribute("title","Registro de nuevo Gasto");

        return "expenses/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Expenses expenses = service.findById(id);
        model.addAttribute("expenses", expenses);
        return "expenses/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        Expenses expenses = service.findById(id);
        model.addAttribute("expenses",expenses);
        return "expenses/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el registro");

        }
        return "redirect:/expenses/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<Expenses> expensesList = service.findAll();
        model.addAttribute("expensesList", expensesList);
        return "expenses/list";
    }

    @PostMapping(value = "/save")
    public String save(Expenses expenses, Model model, RedirectAttributes redirectAttributes){
        try {
            service.save(expenses);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/expenses/list";
    }
}
