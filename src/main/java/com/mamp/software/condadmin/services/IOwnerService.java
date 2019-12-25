package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.Owner;

import java.util.List;

public interface IOwnerService {

    public void save(Owner owner);

    public Owner findById(Integer id);

    public void delete(Integer id);

    public List<Owner> findAll();

    public List<Owner> findByCondom(Integer id);
}
