package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.RepIncVsExp;
import com.mamp.software.condadmin.Models.entities.RepTypeOfExpensesMonthly;

import java.util.List;

public interface IAnnualCountsService {
    public void save(AnnualCounts annualCounts);

    public AnnualCounts findById(Integer id);

    public void delete(Integer id);

    public List<AnnualCounts> findAll();
    
    public AnnualCounts findByYear(Integer year, Integer Id);

    public List<AnnualCounts> findByCondom(Integer id);

    public List<RepIncVsExp> RepIncVsExp(Integer Id);

    public List<RepTypeOfExpensesMonthly> RepTypeOfExpensesMonthly(Integer Id);
}
