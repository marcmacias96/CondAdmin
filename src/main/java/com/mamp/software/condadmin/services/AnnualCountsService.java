package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IAnnualCounts;
import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnnualCountsService implements IAnnualCountsService {
    @Autowired
    private IAnnualCounts dao;

    @Override
    public void save(AnnualCounts annualCounts) {
        dao.save(annualCounts);
    }

    @Override
    public AnnualCounts findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<AnnualCounts> findAll() {
        return (List<AnnualCounts>) dao.findAll();
    }
}
