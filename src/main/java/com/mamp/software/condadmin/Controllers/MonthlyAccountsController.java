package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.*;
import com.mamp.software.condadmin.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/monthlyAccounts")
public class MonthlyAccountsController {
	@Autowired
    private IMonthlyAccountsService srvMonthAcount;

	@Autowired
    private IAnnualCountsService srvAnualAccount;

    @Autowired
    private ICondominiumService srvCond;

    @Autowired
    private IIncomeService srvIncome;

    @Autowired
    private IExpensesService srvExpense;

    @Autowired
    private IUser srvUser;




	@GetMapping(value = "/retrive")
    public String retriveByDate(Model model){
        Calendar fecha = Calendar.getInstance();
        AnnualCounts annualCounts = srvAnualAccount.findByYear(fecha.get(Calendar.YEAR));

        MonthlyAccounts monthlyAccounts = srvMonthAcount.findByMonth(fecha.get(Calendar.MONTH),annualCounts.getIdannualcounts());
        //Aqui voy a buscar por fecha, entonces busco un mounthly Account por el mes y por el año.
        //Para obtener el monthly account debo tener el anual account

        if(monthlyAccounts == null) {
            Integer mes = 0;
            if(fecha.get(Calendar.MONTH) == 1) {
                mes = 12;
            } else {
                mes = fecha.get(Calendar.MONTH) -1;
            }
            monthlyAccounts = srvMonthAcount.findByMonth(mes,annualCounts.getIdannualcounts());
        }

        monthlyAccounts.setIncome(monthlyAccounts.getIncome());
        monthlyAccounts.setExpenses(monthlyAccounts.getExpenses());
        srvMonthAcount.save(monthlyAccounts);
        model.addAttribute("monthlyAccounts", monthlyAccounts);

        model.addAttribute("title", "Ingresos");
        model.addAttribute("title1", "Gastos");
        model.addAttribute("title2", "Balances Mensuales");

        return "monthlyAccounts/card";
    }

    @GetMapping(value = "/repTypeOfIncome/{type}", produces = "application/json")
    public @ResponseBody List<RepTypeOfIncomes> repTypeOfIncome (@PathVariable(value = "type") String type){
        List<RepTypeOfIncomes> repTypeOfIncomesList = srvIncome.repTypeOfIncome(type);
        return  repTypeOfIncomesList;
    }

    @GetMapping(value = "/repTypeOfExpense/{Id}", produces = "application/json")
    public @ResponseBody List<RepTypeOfExpenses> repTypeOfExpense (@PathVariable(value = "Id") Integer Id){
        List<RepTypeOfExpenses> repTypeOfExpensesList = srvExpense.repTypeOfExpenses(Id);
        return  repTypeOfExpensesList;
    }

    @GetMapping(value = "/listJsonIncomes/{id}", produces = "application/json")
    public @ResponseBody List<Income> listJsonIncome(@PathVariable(value = "id") Integer id){
        MonthlyAccounts monthlyAccounts = srvMonthAcount.findById(id);
        return monthlyAccounts.getIncomeList();
    }

    @GetMapping(value = "/listJsonExpenses/{id}", produces = "application/json")
    public @ResponseBody List<Expenses> listJsonExpenses(@PathVariable(value = "id") Integer id){
        MonthlyAccounts monthlyAccounts = srvMonthAcount.findById(id);
        return monthlyAccounts.getExpensesList();
    }

    @GetMapping(value = "/listJsonMonthly/{id}", produces = "application/json")
    public @ResponseBody List<MonthlyAccounts> listJsonMonthly(@PathVariable(value = "id") Integer id){
      List<MonthlyAccounts> monthlyAccountsList = srvMonthAcount.findByYear(id);
      return  monthlyAccountsList;
    }

    @GetMapping(value = "/list/{id}")
    public String list (Model model, Authentication authentication, @PathVariable(value = "id") Integer id){
        AnnualCounts annualCounts = srvAnualAccount.findById(id);
        model.addAttribute("title","Balances Anuales");
        model.addAttribute("annualCounts", annualCounts);
        return "monthlyAccounts/list";
    }


    @GetMapping(value = "/retrive/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        //Esto calcula el balance de los ingresos y egresos y  suma solo las alicuotas pagadas
        MonthlyAccounts monthlyAccounts = srvMonthAcount.findById(id);

        monthlyAccounts.setIncome(monthlyAccounts.getIncome());
        monthlyAccounts.setExpenses(monthlyAccounts.getExpenses());
        srvMonthAcount.save(monthlyAccounts);
        model.addAttribute("monthlyAccounts", monthlyAccounts);
        model.addAttribute("title", "Ingresos");
        model.addAttribute("title1", "Gastos");
        model.addAttribute("title2", "Balances Mensuales");

        return "monthlyAccounts/card";
    }



    @GetMapping(value = "/closeBox/{id}")
    public String closeBox(@PathVariable(value = "id") Integer id){
        //buscamos las cuentas mensuales acutales
        Calendar calendar = Calendar.getInstance();
        //aqui se debe agregar la multa por inpago

        List<Income> inpaymentList = srvIncome.findByState(id);
        for (Income inc : inpaymentList) {
            IncomeDetail multa = new IncomeDetail();
            multa.setType("Multa");
            multa.setDetail("Multa por inpago");
            multa.setValue(0.0f);
            inc.getIncomeDetailList().add(multa);
            srvIncome.save(inc);
        }

        MonthlyAccounts monthlyAccounts = srvMonthAcount.findById(id);
        //obtenemos el corte anual al que pertenece el corte mensual para poder obtener el condominio al que pertenecen las cuentas
        AnnualCounts anualCounts = srvAnualAccount.findById(monthlyAccounts.getAnnualCounts().getIdannualcounts());
        ArrayList<Income> incomeList= new ArrayList<>();
        for (House house: anualCounts.getCondominium().getHouseList()){
            ArrayList<IncomeDetail> incomeDetails = new ArrayList<>();
            IncomeDetail detail = new IncomeDetail();
            detail.setDetail("Pago Alicuota");
            detail.setType("Alicuota");
            detail.setValue(anualCounts.getCondominium().getCostAli());
            incomeDetails.add(detail);
            Income income = new Income();
            income.setDate(calendar);
            income.setCondominium(anualCounts.getCondominium());
            income.setHouse(house);
            income.setState(false);
            income.setIncomeDetailList(incomeDetails);
            income.setValue(0);
            srvIncome.save(income);
            incomeList.add(income);
        }
        //Aqui comprobamos que el corte que realizamos no es el primero que se realiza
        //ya que si es el primero no se podra cobrar del mes en que se abrio la cuenta ya que no tiene alicuotas asignadas
        if(monthlyAccounts.getIncomeList().size() != 0 ){
            //creamos un nuevo corte de cuentas mensuales
            MonthlyAccounts monthlyAccountsNext = new MonthlyAccounts();
            //comprobamos si el mes actual es diciembre
            if(monthlyAccounts.getMonth()==11){
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
            monthlyAccountsNext.setIncomeList(incomeList);
            monthlyAccountsNext.setExpensesList(new ArrayList<Expenses>());
            srvMonthAcount.save(monthlyAccountsNext);
            for (Income inc: monthlyAccountsNext.getIncomeList() ) {
                inc.setMonthlyAccounts(monthlyAccountsNext);
                srvIncome.save(inc);
            }
            monthlyAccounts.setIncome(monthlyAccounts.getIncome());
            monthlyAccounts.setExpenses(monthlyAccounts.getExpenses());
            anualCounts.setIncome(anualCounts.getIncome());
            anualCounts.setExpenses(anualCounts.getExpenses());
            srvAnualAccount.save(anualCounts);
            srvMonthAcount.save(monthlyAccounts);
            return "redirect:/monthlyAccounts/retrive/" + monthlyAccountsNext.getIdmonthlyaccounts();
        } else {
            // si es el primer corte las alicuotas se agregaran al mes actual
            monthlyAccounts.setIncomeList(incomeList);
            srvMonthAcount.save(monthlyAccounts);
            for (Income inc: monthlyAccounts.getIncomeList() ) {
                inc.setMonthlyAccounts(monthlyAccounts);
                srvIncome.save(inc);
            }
            return "redirect:/monthlyAccounts/retrive/" + monthlyAccounts.getIdmonthlyaccounts();
        }
    }


}