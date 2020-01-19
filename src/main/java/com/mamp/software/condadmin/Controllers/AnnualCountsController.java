package com.mamp.software.condadmin.Controllers;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.Condominium;
import com.mamp.software.condadmin.services.IAnnualCountsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/annualCounts")
public class AnnualCountsController {
	@Autowired
    public IAnnualCountsService service;


    @GetMapping(value = "/retrieve/{id}")
    public String retrive(@PathVariable(value = "id") Integer id, Model model){
        AnnualCounts annualCounts = service.findById(id);
        model.addAttribute("annualCounts", annualCounts);
        return "annualCounts/card";
    }

    @GetMapping(value = "/countsDashboard")
    public  String dashboad( Model model){
        Calendar fecha = Calendar.getInstance();
        AnnualCounts annualCounts = service.findByYear(fecha.get(Calendar.YEAR));
        model.addAttribute("annualCounts", annualCounts);
        return "annualCounts/dashboard";
    }


    @GetMapping(value = "/list")
    public String list(Model model){
        List<AnnualCounts> annualCountsList = service.findAll();
        model.addAttribute("annualCountsList", annualCountsList);
        return "annualCounts/list";
    }

}
