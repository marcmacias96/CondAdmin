package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IAnnualCounts;
import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnualCountsService implements IAnnualCountsService {
    @Autowired
    private IAnnualCounts dao;

    @Override
    @Transactional
    public void save(AnnualCounts annualCounts) {
        dao.save(annualCounts);
    }

    @Override
    @Transactional(readOnly=true)
    public AnnualCounts findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<AnnualCounts> findAll() {
        return (List<AnnualCounts>) dao.findAll();
    }

	@Override
	public AnnualCounts findByYear(Integer year) {
		return dao.findByYear(year);
	}
}
