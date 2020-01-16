package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IAnnualCounts;
import com.mamp.software.condadmin.Models.dao.IMonthlyAccounts;
import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.Expenses;
import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;
import com.mamp.software.condadmin.Models.entities.USer;
import com.mamp.software.condadmin.services.IExpensesService;

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
import java.util.TimeZone;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping(value = "/expenses")
public class ExpensesController {
	@Autowired
    public IExpensesService service;
	
	@Autowired
	public IAnnualCounts srvAnual;
	
	@Autowired
	public IMonthlyAccounts srvMonthly;

	@Autowired
    private IUser srvUser;
	
    @GetMapping(value = "/myExpenses")
    public String create(Model model, Authentication authentication){    	
    	USer user = srvUser.findByName(authentication.getName());
    	Expenses expenses = new Expenses();
        model.addAttribute("expenses", expenses);
        model.addAttribute("title","Registro de nuevo Gasto");

        return "cuentas/expenses/form";
    }

    @GetMapping(value = "/retrieve/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Expenses expenses = service.findById(id);
        model.addAttribute("expenses", expenses);
        model.addAttribute("title","Actualizacion de registro de nuevo gasto");
        return "cuentas/expenses/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        Expenses expenses = service.findById(id);
        model.addAttribute("title","Actualizacion de registro de nuevo gasto");
        model.addAttribute("expenses",expenses);
        return "cuentas/expenses/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
    	model.addAttribute("title","eliminacion de registro de nuevo gasto");
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
        model.addAttribute("title","Listado de Gastos");
        model.addAttribute("expensesList", expensesList);
        return "cuentas/expenses/list";
    }

    @PostMapping(value = "/save")
    public String save(@Valid Expenses expenses, Model model, RedirectAttributes redirectAttributes){
    	/*Date fecha = new Date();
    	Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Bogota/Quito"));
    	cal.setTime(fecha);*/
    	int year = expenses.getDate().get(Calendar.YEAR);
    	int month = expenses.getDate().get(Calendar.MONTH);
        try {
        	AnnualCounts annualCount = srvAnual.findByYear(year);
        	MonthlyAccounts monthlyAccount = srvMonthly.findByMonth(month, annualCount.getIdannualcounts());
        	System.out.println(monthlyAccount);
        	expenses.setMonthlyAccounts(monthlyAccount);
            service.save(expenses);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guardar");
            return "cuentas/expenses/form";
        }
        return "redirect:/expenses/list";
    }
}