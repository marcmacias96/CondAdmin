package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.AnnualCounts;

import java.util.List;

public interface IAnnualCountsService {
    public void save(AnnualCounts annualCounts);

    public AnnualCounts findById(Integer id);

    public void delete(Integer id);

    public List<AnnualCounts> findAll();
}
