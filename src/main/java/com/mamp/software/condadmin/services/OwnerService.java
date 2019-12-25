package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IOwner;
import com.mamp.software.condadmin.Models.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OwnerService implements IOwnerService {
    @Autowired
    private IOwner dao;

    @Override
    @Transactional
    public void save(Owner owner) {
        try {
            dao.save(owner);
        }
        catch(Exception ex) {
            throw ex;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public Owner findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    @Transactional(readOnly=true)
    public List<Owner> findAll() {
        return (List<Owner>) dao.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Owner> findByCondom(Integer id) {
        return (List<Owner>) dao.findByCondom(id);
    }
}
