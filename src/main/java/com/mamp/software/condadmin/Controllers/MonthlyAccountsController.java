package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.*;
import com.mamp.software.condadmin.services.IAnnualCountsService;
import com.mamp.software.condadmin.services.IIncomeService;
import com.mamp.software.condadmin.services.IMonthlyAccountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/monthlyAccounts")
public class MonthlyAccountsController {
	@Autowired
    private IMonthlyAccountsService srvMonthAcount;

	@Autowired
    private IAnnualCountsService srvAnualAccount;


    @GetMapping(value = "/create")
    public String create(Model model){
        MonthlyAccounts monthlyAccounts = new MonthlyAccounts();
        model.addAttribute("annualCounts", monthlyAccounts);
        model.addAttribute("title","Registro nuevo de Cuentas Mensuales");

        return "monthlyAccounts/form";
    }

    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        MonthlyAccounts monthlyAccounts = srvMonthAcount.findById(id);
        model.addAttribute("monthlyAccounts", monthlyAccounts);
        return "monthlyAccounts/card";
    }

    @GetMapping(value = "update/{id}")
    public String update(@PathVariable(value = "id") Integer id, Model model){
        //Esto calcula el balance de los ingresos y egresos y  suma solo las alicuotas pagadas
        MonthlyAccounts monthlyAccounts = srvMonthAcount.findById(id);
        float incomes = 0.0f;
        for (Income inc: monthlyAccounts.getIncomeList()) {
            if(inc.getState()){
                for (IncomeDetail det: inc.getIncomeDetailList()) {
                    incomes+= det.getValue();
                }
            }

        }
        monthlyAccounts.setIncome(incomes);
        float expenses = 0.0f;
        for (Expenses exp: monthlyAccounts.getExpensesList()) {
            for (ExpenseDetail det: exp.getExpenseDetailList()) {
                expenses+= det.getValue();
            }
        }
        monthlyAccounts.setExpenses(expenses);
        model.addAttribute("annualCounts",monthlyAccounts);
        return "monthlyAccounts/form";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes redirectAttributes){
        try {
            srvMonthAcount.delete(id);
            redirectAttributes.addFlashAttribute("message","El registro se elimino exitosamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","Error al eliminar el resgistro");

        }
        return "redirect:/monthlyAccounts/list";
    }

    @GetMapping(value = "/list")
    public String list(Model model){
        List<MonthlyAccounts> monthlyAccountsList = srvMonthAcount.findAll();
        model.addAttribute("monthlyAccountsList", monthlyAccountsList);
        return "monthlyAccounts/list";
    }

    @PostMapping(value = "/save")
    public String save(MonthlyAccounts monthlyAccounts, Model model, RedirectAttributes redirectAttributes){
        try {
            srvMonthAcount.save(monthlyAccounts);
            redirectAttributes.addFlashAttribute("message","Registro guardado con exito");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message","No se pudo guerdar");
        }
        return "redirect:/monthlyAccounts/list";
    }
    
    @GetMapping(value = "/closeBox/{id}")
    public String closeBox(@PathVariable(value = "id") Integer id){
        //aqui se debe agregar la multa por inpago
        //buscamos las cuentas mensuales acutales
        MonthlyAccounts monthlyAccounts = srvMonthAcount.findById(id);
        //creamos un nuevo corte de cuentas mensuales
        MonthlyAccounts monthlyAccountsNext = new MonthlyAccounts();
        //obtenemos el corte anual al que pertenece el corte mensual para poder obtener el condominio al que pertenecesnlas cuentas
        AnnualCounts anualCounts = srvAnualAccount.findById(monthlyAccounts.getAnnualCounts().getIdannualcounts());
        //comprobamos si el mes actual es diciembre
        if(monthlyAccounts.getMonth()==12){
            // Si es diciembre el mes siguiente pertenece a otro corte anual que no existe asi que lo creamos
            AnnualCounts newAnnualCounts = new AnnualCounts();
            newAnnualCounts.setCondominium(anualCounts.getCondominium());
            newAnnualCounts.setIncome(0.0f);
            newAnnualCounts.setExpenses(0.0f);
            newAnnualCounts.setYear(anualCounts.getYear()+1);
            srvAnualAccount.save(newAnnualCounts);
            newAnnualCounts = srvAnualAccount.findByYear(newAnnualCounts.getYear());
            monthlyAccountsNext.setAnnualCounts(newAnnualCounts);
            monthlyAccountsNext.setMonth(1);
        } else {
            // sin o es dociembre entonces seteamos el mes siguiente
            monthlyAccountsNext.setMonth(monthlyAccounts.getMonth()+1);
        }
        //seguimos construyendo el objeto de corte mensual al que le tenemos que agregar un array de las alicuotas por pagar
        //esto hizo que se modifique la relacion de MonthlyAcounts con Incomes para hacer mas facil el proceso
        monthlyAccountsNext.setExpenses(0.0f);
        monthlyAccountsNext.setIncome(0.0f);
        monthlyAccountsNext.setAnnualCounts(anualCounts);
        ArrayList<Income> incomeList= new ArrayList<Income>();
        ArrayList<IncomeDetail> incomeDetails = new ArrayList<IncomeDetail>();
        IncomeDetail detail = new IncomeDetail();
        detail.setDetail("Pago Alicuota");
        detail.setType("Alicuota");
        incomeDetails.add(detail);
        for (House house: anualCounts.getCondominium().getHouseList()){
          Income income = new Income();
          income.setCondominium(anualCounts.getCondominium());
          income.setHouse(house);
          income.setState(false);
          income.setIncomeDetailList(incomeDetails);
          income.setValue(0.0f);
          incomeList.add(income);
        }
        monthlyAccountsNext.setIncomeList(incomeList);
        srvMonthAcount.save(monthlyAccountsNext);
        return "";
    }
}
