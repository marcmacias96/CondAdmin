package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.Condominium;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ICondominiumService {

    public void save(Condominium condominium);

    public Condominium findById(Integer id);

    public void delete(Integer id);

    public List<Condominium> findAll();

    public Condominium findByUser (Integer id);
}
