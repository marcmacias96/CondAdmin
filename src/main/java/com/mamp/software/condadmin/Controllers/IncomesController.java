package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IAnnualCounts;
import com.mamp.software.condadmin.Models.dao.IMonthlyAccounts;
import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.ExpenseDetail;
import com.mamp.software.condadmin.Models.entities.Expenses;
import com.mamp.software.condadmin.Models.entities.IncomeDetail;
import com.mamp.software.condadmin.Models.entities.Income;
import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;
import com.mamp.software.condadmin.Models.entities.USer;
import com.mamp.software.condadmin.services.ICondominiumService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import java.util.TimeZone;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/incomes")
@SessionAttributes({"details"})
public class IncomesController {
	@Autowired
    public IIncomeService service;
	
	@Autowired
	public IAnnualCounts srvAnual;
	
	@Autowired
	public IMonthlyAccounts srvMonthly;
	
	@Autowired
    private IUser srvUser;

	@Autowired
    private ICondominiumService srvCond;

	@Autowired
    private  IIncomeService srvIncome;
	
    @GetMapping(value = "/create")
    public String create(Model model){
    	
        Income income = new Income();
        model.addAttribute("income", income);
        model.addAttribute("title","Registro de nuevo Ingreso");
        model.addAttribute("details", new ArrayList<IncomeDetail>());

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

    @GetMapping(value = "paid/{id}")
    public String paid(@PathVariable(value = "id") Integer id, Model model){
        Income income = service.findById(id);
        income.setState(true);
        srvIncome.save(income);
        model.addAttribute("income",income);
        return "redirect:/monthlyAccounts/retrive";
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
    /*/myIncomes*/
    @GetMapping(value = "/list")
    public String list(Model model, Authentication authentication){
    	USer user = srvUser.findByName(authentication.getName());
    	Condominium condominium = srvCond.findByUser(user.getIdUser());
        List<Income> incomeList = service.findByCondom(condominium.getIdcondominium());
        model.addAttribute("title","Listado de Ingresos");
        model.addAttribute("incomeList", incomeList);
        return "cuentas/incomes/list";
    }

    @PostMapping(value = "/save")
    public String save(@Valid Income income, Model model, RedirectAttributes redirectAttributes, Authentication authentication, @SessionAttribute(value="details") List<IncomeDetail> detalles
            , SessionStatus session){
    	USer user = srvUser.findByName(authentication.getName());
        Condominium condominium = srvCond.findByUser(user.getIdUser());
    	int year = income.getDate().get(Calendar.YEAR);
    	int month = income.getDate().get(Calendar.MONTH);
    	try {
    		AnnualCounts annualCount = srvAnual.findByYear(year);
    		if(annualCount == null) {
        	    annualCount = new AnnualCounts();
        	    annualCount.setYear(year);
        	    annualCount.setExpenses(0.0f);
        	    annualCount.setIncome(0.0f);
        	    annualCount.setCondominium(condominium);
        	    srvAnual.save(annualCount);
            }
        	MonthlyAccounts monthlyAccount = srvMonthly.findByMonth(month, annualCount.getIdannualcounts());
        	if(monthlyAccount == null ) {
        	    monthlyAccount= new MonthlyAccounts();
        	    monthlyAccount.setExpenses(0.0f);
        	    monthlyAccount.setIncome(0.0f);
        	    monthlyAccount.setMonth(month);
        	    monthlyAccount.setAnnualCounts(annualCount);
        	    srvMonthly.save(monthlyAccount);
            }
        	//income.setMonthlyAccounts(monthlyAccount);
        	income.setCondominium(condominium);
        	income.setIncomeDetailList(detalles);
    		service.save(income);
    		List<Income> ex = service.findByCondom(condominium.getIdcondominium());
    		session.setComplete();
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guardar");
            return "cuentas/incomes/form";
        }
        return "redirect:/incomes/list";
    }
    
    @PostMapping(value="/addDetailIncome", produces="application/json")
    public @ResponseBody List<IncomeDetail> addDetailIncomes(@RequestBody IncomeDetail detail, @SessionAttribute(value="details") List<IncomeDetail> detalles) {
        detalles.add(detail);
        return detalles;
    }
}