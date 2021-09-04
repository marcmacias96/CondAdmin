package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.Expenses;
import com.mamp.software.condadmin.Models.entities.RepTypeOfExpenses;

import java.util.List;

public interface IExpensesService {
    public void save(Expenses expenses);

    public Expenses findById(Integer id);

    public void delete(Integer id);

    public List<Expenses> findAll();

    public List<Expenses> findByCondom(Integer id);

    public List<RepTypeOfExpenses>repTypeOfExpenses(Integer Id,Integer month);
}
