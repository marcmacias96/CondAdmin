package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IAnnualCounts;
import com.mamp.software.condadmin.Models.entities.AnnualCounts;
import com.mamp.software.condadmin.Models.entities.RepIncVsExp;
import com.mamp.software.condadmin.Models.entities.RepTypeOfIncomes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnualCountsService implements IAnnualCountsService {
    @Autowired
    private IAnnualCounts dao;

    @PersistenceContext
    private EntityManager em;

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

    @Override
    public List<AnnualCounts> findByCondom(Integer id) {
        return (List<AnnualCounts>) dao.findByCondom(id);
    }

    @Override
    public List<RepIncVsExp> RepIncVsExp(Integer year) {
        StoredProcedureQuery consulta = em.createStoredProcedureQuery("incomesVsExpensesByMonths");
        consulta.registerStoredProcedureParameter("Year", Integer.class, ParameterMode.IN);
        consulta.setParameter("Year", year);
        consulta.execute();
        List<Object[]> datos = consulta.getResultList();
        return datos.stream()
                .map(r -> new RepIncVsExp((Integer) r[0], (Double)r[1], (Double)r[2]))
                .collect(Collectors.toList());
    }
}
