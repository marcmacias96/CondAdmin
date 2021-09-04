package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IAnnualCounts;
import com.mamp.software.condadmin.Models.dao.IMonthlyAccounts;
import com.mamp.software.condadmin.Models.entities.*;
import com.mamp.software.condadmin.services.CondominiumService;
import com.mamp.software.condadmin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService srvUser;

    @Autowired
    private CondominiumService srvCond;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public IAnnualCounts srvAnual;

    @Autowired
    public IMonthlyAccounts srvMonthly;

    @GetMapping(value="/create")
    public String registro(Model model,  RedirectAttributes flash) {
        try{
            USer user = new USer();
            model.addAttribute("user", user);
            model.addAttribute("title", "Nuevo registro");
        }catch (Exception e){
            flash.addFlashAttribute("error", "Ocurrio un error reintente");
            return "user/form";
        }
        return "user/form";
    }

    @PostMapping(value="/save")
    public String save(@Valid USer user, BindingResult result, Model model,
                       RedirectAttributes flash) {
        try {
            if(result.hasErrors())
            {
                if(user.getIdUser() == null) {
                    model.addAttribute("title","Nuevo registro");
                }
                else {
                    model.addAttribute("title","Actualización de registro");
                }

                return "/user/form";
            }
            Condominium condominium = new Condominium();
            condominium.setName(user.getCondName());
            condominium.setCostAli(0.0f);
            condominium.setReference(" ");
            condominium.setSector(" ");
            condominium.setStreet1(" ");
            condominium.setStreet2(" ");
            String pass = user.getPassword();
            user.setPassword(passwordEncoder.encode(pass));
            user.getRoleList().add(new Role("ROLE_USER-ADMIN"));
            srvUser.save(user);
           // user = srvUser.findbyName(user.getName());
            condominium.setuSer(user);
            srvCond.save(condominium);
            condominium = srvCond.findByUser(user.getIdUser());
            //Balances
            Calendar fecha = Calendar.getInstance();
            int year =fecha.get(Calendar.YEAR);
            int month = fecha.get(Calendar.MONTH);
            AnnualCounts annualCount = new AnnualCounts();
            annualCount.setYear(year);
            annualCount.setExpenses(0.0f);
            annualCount.setIncome(0.0f);
            annualCount.setCondominium(condominium);
            srvAnual.save(annualCount);
            MonthlyAccounts monthlyAccount= new MonthlyAccounts();
            monthlyAccount.setExpenses(0.0f);
            monthlyAccount.setIncome(0.0f);
            monthlyAccount.setMonth(month);
            monthlyAccount.setAnnualCounts(annualCount);
            srvMonthly.save(monthlyAccount);
            flash.addFlashAttribute("success", "El registro fue guardado con éxito.");
        }
        catch(Exception ex) {
            flash.addFlashAttribute("error", "El registro no pudo ser guardado.");
            System.out.println(ex);
        }
        return "redirect:/login";
    }
}