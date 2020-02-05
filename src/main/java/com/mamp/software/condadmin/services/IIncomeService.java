package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.Income;
import com.mamp.software.condadmin.Models.entities.RepTypeOfIncomes;

import java.util.List;

public interface IIncomeService {
    public void save(Income income);

    public Income findById(Integer id);

    public void delete(Integer id);

    public List<Income> findAll();
    
    public List<Income> findByCondom(Integer id);

    public List<Income> findByHouse(Integer id);

    public  List<Income>  findByMonthAndYear (Integer month, Integer year);

    public List<Income> findByState (Integer id);

    public List<RepTypeOfIncomes> repTypeOfIncome (Integer ID);
}
