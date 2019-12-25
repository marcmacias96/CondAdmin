package com.mamp.software.condadmin.Models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "ROLE", uniqueConstraints= {@UniqueConstraint(columnNames= {"IDUSER", "NAME"})})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDROLE")
    private Integer idRole;

    @Column(name = "NAME", unique = true)
    @Size(max = 15)
    private String name;


    public Role() {
    }

    public Role(String name) {
        super();
        this.name = name;
    }

    public Integer getIdRole() {
        return idRole;
    }


    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
