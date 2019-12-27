package com.mamp.software.condadmin.Models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class USer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDUSER")
    private Integer idUser;

    @Column(name = "NAME", unique = true)
    @Size(max = 15)
    private String name;

    @Column(name = "PASSWORD", length = 60)
    @Size(min = 6)
    private String password;

    @Transient
    private String condName;

    @JsonIgnore
    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "IDUSER")
    private List<Role> roleList;

    @JsonIgnore
    @OneToMany(mappedBy = "uSer", fetch = FetchType.LAZY)
    private List<Condominium> condominiumList;

    public String getCondName() {
        return condName;
    }

    public void setCondName(String condName) {
        this.condName = condName;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        if(roleList == null)
            roleList = new ArrayList<>();
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Condominium> getCondominiumList() {
        return condominiumList;
    }

    public void addCond(Condominium condominium) {
        if(this.condominiumList ==  null){
            this.condominiumList = new ArrayList<Condominium>();
        }
        this.condominiumList.add(condominium);
    }
}
