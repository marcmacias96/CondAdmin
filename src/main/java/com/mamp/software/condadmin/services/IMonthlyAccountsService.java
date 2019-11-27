package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;

import java.util.List;

public interface IMonthlyAccountsService {
    public void save(MonthlyAccounts monthlyAccounts);

    public MonthlyAccounts findById(Integer id);

    public void delete(Integer id);

    public List<MonthlyAccounts> findAll();
}
