package com.mamp.software.condadmin.Models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "HOUSE")
public class House implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDHOUSE")
    private Integer idhouse;

    @Column(name = "NUMBER")
    private Integer number;

    @Column(name = "BLOCK")
    @Size(max=5)
    @NotEmpty
    private String block;

    @Transient
    private Integer ownerId;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    //RELATIONS
    @JoinColumn(name = "IDOWNER", referencedColumnName = "IDOWNER")
    @ManyToOne
    private Owner owner;

    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;

    @JsonIgnore
    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    private List<Income> incomeList;

    /**/
    public House(){
        super();
    }

    private House(Integer idhouse){
        super();
        this.idhouse = idhouse;
    }

    /**/
    public Integer getIdhouse() {
        return idhouse;
    }

    public void setIdhouse(Integer idhouse) {
        this.idhouse = idhouse;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Condominium getCondominium() {
        return condominium;
    }

    public void setCondominium(Condominium condominium) {
        this.condominium = condominium;
    }
}
