package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.ICondominium;
import com.mamp.software.condadmin.Models.entities.Condominium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CondominiumService implements ICondominiumService{
    @Autowired
    private ICondominium dao;

    @Override
    @Transactional
    public void save(Condominium condominium) {
        dao.save(condominium);
    }

    @Override
    @Transactional(readOnly=true)
    public Condominium findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Condominium> findAll() {
        return (List<Condominium>) dao.findAll();
    }
}
