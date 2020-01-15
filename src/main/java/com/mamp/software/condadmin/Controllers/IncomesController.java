package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.Income;
import com.mamp.software.condadmin.Models.entities.USer;
import com.mamp.software.condadmin.services.IIncomeService;

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
@RequestMapping(value = "/incomes")
public class IncomesController {
	@Autowired
    public IIncomeService service;
	
	@Autowired
    private IUser srvUser;

    @GetMapping(value = "/create")
    public String create(Model model, Authentication authentication){
    	USer user = srvUser.findByName(authentication.getName());
        Income income = new Income();
        model.addAttribute("income", income);
        model.addAttribute("title","Registro de nuevo Ingreso");

        return "cuentas/incomes/form";
    }

    @GetMapping(value = "/retrieve/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Income income = service.findById(id);
        model.addAttribute("income", income);
        model.addAttribute("title","Actualizacion de registro de nuevo ingreso");
        return "cuentas/incomes/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        Income income = service.findById(id);
        model.addAttribute("title","Actualizacion de registro de nuevo ingreso");
        model.addAttribute("income",income);
        return "cuentas/incomes/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
    	model.addAttribute("title","eliminacion de registro de nuevo ingreso");
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
        model.addAttribute("title","Listado de Ingresos");
        model.addAttribute("incomeList", incomeListList);
        return "cuentas/incomes/list";
    }

    @PostMapping(value = "/save")
    public String save(@Valid  Income income, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        try {
            service.save(income);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guardar");
        }
        return "redirect:/incomes/list";
    }
}
