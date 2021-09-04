package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IIncome;
import com.mamp.software.condadmin.Models.entities.Income;
import com.mamp.software.condadmin.Models.entities.RepTypeOfIncomes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncomeService implements IIncomeService {
    @Autowired
    private IIncome dao;

    @PersistenceContext
    private EntityManager em;

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
    
    @Override
    public List<Income> findByCondom(Integer id){
    	return (List<Income>) dao.findByCondom(id);
    }

    @Override
    public List<Income> findByHouse(Integer id) {
        return  (List<Income>) dao.findByHouse(id);
    }

    @Override
    public  List<Income>  findByMonthAndYear(Integer month, Integer year) {
        return dao.findByMonthAndYear(month,year);
    }

    @Override
    public List<Income> findByState(Integer id) {
        return  (List<Income>) dao.findByState(id);
    }

    @Override
    public List<RepTypeOfIncomes> repTypeOfIncome(Integer ID) {
        StoredProcedureQuery consulta = em.createStoredProcedureQuery("incomesByType");
        consulta.registerStoredProcedureParameter("ID", Integer.class, ParameterMode.IN);
        consulta.setParameter("ID", ID);
        consulta.execute();
        List<Object[]> datos = consulta.getResultList();
        return datos.stream()
                .map(r -> new RepTypeOfIncomes((Integer)r[0], (Double) r[1]))
                .collect(Collectors.toList());
    }


}
