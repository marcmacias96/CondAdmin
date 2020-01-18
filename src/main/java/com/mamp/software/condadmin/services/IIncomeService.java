package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.Income;

import java.util.List;

public interface IIncomeService {
    public void save(Income income);

    public Income findById(Integer id);

    public void delete(Integer id);

    public List<Income> findAll();
    
    public List<Income> findByCondom(Integer id);
}
