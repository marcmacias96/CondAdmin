package com.mamp.software.condadmin.Controllers;
import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.*;
import com.mamp.software.condadmin.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/annualCounts")
public class AnnualCountsController {

	@Autowired
    public IAnnualCountsService srvAnualAccount;

    @Autowired
    private IUser srvUser;

    @Autowired
    private IMonthlyAccountsService srvMonthly;

    @Autowired
    private ICondominiumService srvCond;

    @Autowired
    private IAnnualCountsService srvAnual;

    @GetMapping(value = "/retrieve")
    public String retriveByDate(Model model, Authentication authentication, RedirectAttributes flash){
        try{
            USer user = srvUser.findByName(authentication.getName());
            Condominium condominium = srvCond.findByUser(user.getIdUser());
            Calendar fecha = Calendar.getInstance();
            AnnualCounts annualCounts = srvAnualAccount.findByYear((fecha.get(Calendar.YEAR)),condominium.getIdcondominium());
            annualCounts.setIncome(annualCounts.getIncome());
            annualCounts.setExpenses(annualCounts.getExpenses());
            model.addAttribute("annualCounts", annualCounts);
            model.addAttribute("title", "Balances Mensuales");
            model.addAttribute("title1", "Gastos");
            model.addAttribute("title2", "Balances Anuales");

        }catch (Exception e){
            flash.addFlashAttribute("error", "El registro no pudo ser guardado.");
            return "annualAcounts/form";
        }

        return "annualAcounts/card";
    }

    @GetMapping(value = "/retrieve/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash ){

        try {
            AnnualCounts anualCounts = srvAnualAccount.findById(id);
            anualCounts.setIncome(anualCounts.getIncome());
            anualCounts.setExpenses(anualCounts.getExpenses());
            srvAnualAccount.save(anualCounts);
            model.addAttribute("anualCounts", anualCounts);
            model.addAttribute("title", "Balances Mensuales");
            model.addAttribute("title1", "Gastos");
            model.addAttribute("title2", "Balances Anuales");
        }catch (Exception e){
            flash.addFlashAttribute("error", "El registro no pudo ser guardado.");
            return "redirect:/annualAcounts/list";
        }
        return "annualAcounts/card";
    }

    @GetMapping(value = "closeBox/{id}")
    public String closeBoxA(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        AnnualCounts anualCounts = null;
        try {
            anualCounts = srvAnualAccount.findById(id);
            anualCounts.setIncome(anualCounts.getIncome());
            anualCounts.setExpenses(anualCounts.getExpenses());
            model.addAttribute("title","eliminacion de registro de nuevo ingreso");
            AnnualCounts annualCountsYearNext = srvAnualAccount.findByYear(anualCounts.getYear()+1,anualCounts.getCondominium().getIdcondominium());
            return "redirect:/annualAcounts/retrive/"+annualCountsYearNext.getIdannualcounts();
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","No se ha hecho el corte mensual de Diciembre, no existe un año posterior");
            return "redirect:/annualAcounts/retrive/"+ anualCounts.getIdannualcounts();
        }
    }


    @GetMapping(value = "/list")
    public String list (Model model, Authentication authentication, RedirectAttributes redirectAttributes){
        try{
            USer adminUser = srvUser.findByName(authentication.getName());
            Condominium condominium = srvCond.findByUser(adminUser.getIdUser());
            model.addAttribute("title","Balances Anuales");
            model.addAttribute("condominium", condominium);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error","No se pudo listar");
            return "redirect:/";
        }

        return "annualAcounts/list";
    }

    @GetMapping(value = "/listJsonMonthly/{id}", produces = "application/json")
    public @ResponseBody List<MonthlyAccounts> listJsonMonthly(@PathVariable(value = "id") Integer id){
        AnnualCounts annualCounts = srvAnualAccount.findById(id);
        return annualCounts.getMonthlyAccountsList();
    }

    @GetMapping(value = "/listJson/{id}", produces = "application/json")
    public @ResponseBody List<AnnualCounts> listJson(@PathVariable(value = "id") Integer id, Model model){
        List<AnnualCounts> annualCountsList = srvAnualAccount.findByCondom(id);
        model.addAttribute("title", "Balances Mensuales");
        return annualCountsList;
    }

    @GetMapping(value = "/repIncVsExp/{year}", produces = "application/json")
    public @ResponseBody List<RepIncVsExp> repIncVsExp (@PathVariable(value = "year") Integer year){
        List<RepIncVsExp> repTypeOfExpensesList = srvAnual.RepIncVsExp(year);
        return  repTypeOfExpensesList ;
    }

    @GetMapping(value = "/repTypeOfExpensesMonthly/{Id}", produces =  "application/json")
    public @ResponseBody List<RepTypeOfExpensesMonthly> repTypeOfExpensesMonthlies (@PathVariable(value = "Id") Integer Id){
        List<RepTypeOfExpensesMonthly> repTypeOfExpensesMonthlyList= srvAnual.RepTypeOfExpensesMonthly(Id);
        return repTypeOfExpensesMonthlyList;
    }


}