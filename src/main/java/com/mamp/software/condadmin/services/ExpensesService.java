package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IExpenses;
import com.mamp.software.condadmin.Models.entities.Expenses;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExpensesService implements IExpensesService {
    @Autowired
    private IExpenses dao;

    @Override
    public void save(Expenses expenses) {
        dao.save(expenses);
    }

    @Override
    public Expenses findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Expenses> findAll() {
        return (List<Expenses>) dao.findAll();
    }
}
