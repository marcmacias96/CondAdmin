package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.dao.IUser;
import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.Models.entities.USer;
import com.mamp.software.condadmin.services.IAnnualCountsService;

import com.mamp.software.condadmin.services.ICondominiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/annualCounts")
public class AnnualCountsController {
	@Autowired
    public IAnnualCountsService srvAnual;

    @Autowired
    private IUser srvUser;

    @Autowired
    private ICondominiumService srvCond;


    @GetMapping(value = "/retrieve/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        AnnualCounts annualCounts = srvAnual.findById(id);
        model.addAttribute("annualCounts", annualCounts);
        return "annualAcounts/card";
    }

    @GetMapping(value = "/countsDashboard")
    public  String dashboad( Model model){
        Calendar fecha = Calendar.getInstance();
        AnnualCounts annualCounts = srvAnual.findByYear(fecha.get(Calendar.YEAR));
        model.addAttribute("annualCounts", annualCounts);
        return "annualAcounts/dashboard";
    }


    @GetMapping(value = "/listJson/{id}", produces = "application/json")
    public @ResponseBody List<AnnualCounts> listJson(@PathVariable(value = "id") Integer id){
        List<AnnualCounts> annualCountsList = srvAnual.findByCondom(id);
        return annualCountsList;
    }

    @GetMapping(value = "/list")
    public String list (Model model, Authentication authentication){
        USer adminUser = srvUser.findByName(authentication.getName());
        Condominium condominium = srvCond.findByUser(adminUser.getIdUser());
        model.addAttribute("title","Cuentas Anuales");
        model.addAttribute("condominium", condominium);
        return "annualAcounts/list";
    }

}
