package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IExpenses;
import com.mamp.software.condadmin.Models.entities.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExpensesService implements IExpensesService {
    @Autowired
    private IExpenses dao;

    @Override
    @Transactional
    public void save(Expenses expenses) {
        dao.save(expenses);
    }

    @Override
    @Transactional(readOnly=true)
    public Expenses findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Expenses> findAll() {
        return (List<Expenses>) dao.findAll();
    }
}
