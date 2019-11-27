package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IMonthlyAccounts;
import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MonthlyAccountsService implements IMonthlyAccountsService {
    @Autowired
    private IMonthlyAccounts dao;


    @Override
    public void save(MonthlyAccounts monthlyAccounts) {
        dao.save(monthlyAccounts);
    }

    @Override
    public MonthlyAccounts findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<MonthlyAccounts> findAll() {
        return (List<MonthlyAccounts>) dao.findAll();
    }
}
