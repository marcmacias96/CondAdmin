package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.dao.IOwner;
import com.mamp.software.condadmin.Models.entities.Owner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OwnerService implements IOwnerService {
    @Autowired
    private IOwner dao;

    @Override
    public void save(Owner owner) {
        dao.save(owner);
    }

    @Override
    public Owner findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<Owner> findAll() {
        return (List<Owner>) dao.findAll();
    }
}
