package com.mamp.software.condadmin.Models.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "OWNER")
public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOWNER")
    private Integer Id;

    @Column(name = "NAME")
    @Size(max=55)
    private String name;

    @Column(name = "LASTNAME")
    @Size(max=55)
    private String lastName;

    @Column(name = "IDCARD")
    @Size(max=10)
    private String ci;

    @Column(name = "EMAIL")
    @Size(max=50)
    private String email;

    //Relations
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<House> houselist;

    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;

    public Owner () {
        super();
    }

    public Owner (String cedula){
        super();
        this.ci = cedula;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }
}
