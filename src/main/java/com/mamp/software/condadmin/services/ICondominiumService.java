package com.mamp.software.condadmin.services;

import com.mamp.software.condadmin.Models.entities.Condominium;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ICondominiumService {

    @PreAuthorize("hasRole('ROLE_ADMIN-USER')")
    public void save(Condominium condominium);

    public Condominium findById(Integer id);

    @PreAuthorize("hasRole('ROLE_ADMIN-USER')")
    public void delete(Integer id);

    public List<Condominium> findAll();

    public Condominium findByUser (Integer id);
}
