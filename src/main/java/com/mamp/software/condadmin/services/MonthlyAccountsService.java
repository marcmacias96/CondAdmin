package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IMonthlyAccounts;
import com.mamp.software.condadmin.Models.entities.MonthlyAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MonthlyAccountsService implements IMonthlyAccountsService {
    @Autowired
    private IMonthlyAccounts dao;


    @Override
    @Transactional
    public void save(MonthlyAccounts monthlyAccounts) {
        dao.save(monthlyAccounts);
    }

    @Override
    @Transactional(readOnly=true)
    public MonthlyAccounts findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<MonthlyAccounts> findAll() {
        return (List<MonthlyAccounts>) dao.findAll();
    }

	@Override
	public MonthlyAccounts findByMonth(Integer month, Integer id) {

        return dao.findByMonth(month,id);
	}

    @Override
    public List<MonthlyAccounts> findByYear( Integer id){
        return   (List<MonthlyAccounts>) dao.findByYear(id);
    }


}
