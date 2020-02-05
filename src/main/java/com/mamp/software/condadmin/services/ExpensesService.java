package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IExpenses;
import com.mamp.software.condadmin.Models.entities.Expenses;
import com.mamp.software.condadmin.Models.entities.RepTypeOfExpenses;
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
public class ExpensesService implements IExpensesService {
    @Autowired
    private IExpenses dao;

    @PersistenceContext
    private EntityManager em;

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

    @Override
    public List<Expenses> findByCondom(Integer id) {
        return  (List<Expenses>) dao.findByCondom(id);
    }

    @Override
    public List<RepTypeOfExpenses> repTypeOfExpenses(Integer Id,Integer month) {
        StoredProcedureQuery consulta = em.createStoredProcedureQuery("repTypeOfExpensesOnMonthByCondom");
        consulta.registerStoredProcedureParameter("Id", Integer.class, ParameterMode.IN);
        consulta.registerStoredProcedureParameter("month", Integer.class, ParameterMode.IN);
        consulta.setParameter("Id", Id);
        consulta.setParameter("month", month);
        consulta.execute();
        List<Object[]> datos = consulta.getResultList();
        return datos.stream()
                .map(r -> new RepTypeOfExpenses((String)r[0], (Double)r[1]))
                .collect(Collectors.toList());
    }
}
