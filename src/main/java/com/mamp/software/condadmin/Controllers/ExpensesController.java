package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IAnnualCounts;
import com.mamp.software.condadmin.Models.dao.IMonthlyAccounts;
import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.*;
import com.mamp.software.condadmin.services.ICondominiumService;
import com.mamp.software.condadmin.services.IExpensesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.*;

@Controller
@RequestMapping(value = "/expenses")
@SessionAttributes({"details"})
public class ExpensesController {
	@Autowired
    public IExpensesService service;
	
	@Autowired
	public IAnnualCounts srvAnual;
	
	@Autowired
	public IMonthlyAccounts srvMonthly;

	@Autowired
    private IUser srvUser;

	@Autowired
    private ICondominiumService srvCond;
	
    @GetMapping(value = "/create/{id}")
    public String create(Model model, @PathVariable(value = "id") Integer id){
        Optional<MonthlyAccounts> monthlyAccounts = srvMonthly.findById(id);
    	Expenses expenses = new Expenses();
    	expenses.setIdMonth(monthlyAccounts.get().getIdmonthlyaccounts());
        model.addAttribute("expenses", expenses);
        model.addAttribute("title","Registro de nuevo Gasto");
        model.addAttribute("details", new ArrayList<ExpenseDetail>());
        return "cuentas/expenses/form";
    }

    @GetMapping(value = "/retrieve/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        Expenses expenses = service.findById(id);
        model.addAttribute("expenses", expenses);
        model.addAttribute("title","Expenses");
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
        return "redirect:/expenses/myExpenses";
    }

    @GetMapping(value = "/myExpenses")
    public String list(Model model, Authentication authentication){
        //Aqui tomamos el usuario loggeado y obtenemos el condominio al que pertenece
        USer user = srvUser.findByName(authentication.getName());
        //Cree una busqueda especifica en el dao para obtener los gastos de solo el condominio deseado (findbyCondom)
        Condominium condominium = srvCond.findByUser(user.getIdUser());
        List<Expenses> expensesList = service.findByCondom(condominium.getIdcondominium());
        model.addAttribute("title","Listado de Gastos");
        model.addAttribute("expensesList", expensesList);
        return "cuentas/expenses/list";
    }

    @PostMapping(value = "/save")
    public String save(@Valid Expenses expenses, Model model, RedirectAttributes redirectAttributes, Authentication authentication, @SessionAttribute(value="details") List<ExpenseDetail> detalles
            , SessionStatus session){

        try {
            USer user = srvUser.findByName(authentication.getName());
            Condominium condominium = srvCond.findByUser(user.getIdUser());
            Optional<MonthlyAccounts> monthlyAccounts = srvMonthly.findById(expenses.getIdMonth());
            expenses.setMonthlyAccounts(monthlyAccounts.get());
        	expenses.setCondominium(condominium);
        	//guardamos la lista de detalles en el maestro
        	expenses.setExpenseDetailList(detalles);
            service.save(expenses);
            List<Expenses> ex = service.findByCondom(condominium.getIdcondominium());
            //cerramos la sesion
            session.setComplete();
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guardar");
            return "cuentas/expenses/form";
        }
        return "redirect:/expenses/myExpenses";
    }

    @PostMapping(value="/addDetailExpenses", produces="application/json")
    public @ResponseBody List<ExpenseDetail> addDetailExpenses(@RequestBody ExpenseDetail detail, @SessionAttribute(value="details") List<ExpenseDetail> detalles) {
        detalles.add(detail);
        return detalles;
    }
}