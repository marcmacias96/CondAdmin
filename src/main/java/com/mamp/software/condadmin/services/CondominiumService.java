package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.ICondominium;
import com.mamp.software.condadmin.Models.entities.Condominium;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CondominiumService implements ICondominiumService{
    @Autowired
    private ICondominium dao;

    @Override
    public void save(Condominium condominium) {
        dao.save(condominium);
    }

    @Override
    public Condominium findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Condominium> findAll() {
        return (List<Condominium>) dao.findAll();
    }
}
