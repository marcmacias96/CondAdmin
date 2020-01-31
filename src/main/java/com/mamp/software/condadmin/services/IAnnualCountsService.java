package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.RepIncVsExp;

import java.util.List;

public interface IAnnualCountsService {
    public void save(AnnualCounts annualCounts);

    public AnnualCounts findById(Integer id);

    public void delete(Integer id);

    public List<AnnualCounts> findAll();
    
    public AnnualCounts findByYear(Integer year);

    public List<AnnualCounts> findByCondom(Integer id);

    public List<RepIncVsExp> RepIncVsExp(Integer year);
}
