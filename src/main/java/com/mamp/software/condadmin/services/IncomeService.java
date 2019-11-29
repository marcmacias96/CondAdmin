package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IIncome;
import com.mamp.software.condadmin.Models.entities.Income;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeService implements IIncomeService {
    @Autowired
    private IIncome dao;

    @Override
    public void save(Income income) {
        dao.save(income);
    }

    @Override
    public Income findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Income> findAll() {
        return (List<Income>) dao.findAll();
    }
}
