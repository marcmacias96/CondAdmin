package com.mamp.software.condadmin.Models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CONDOMINIUM")
public class Condominium implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDCONDOM")
    private Integer idcondominium;

    @Column(name = "NAME")
    @Size(max=55)
    @NotEmpty
    private String name;

    @Column(name = "SECTOR")
    @Size(max=55)
    @NotEmpty
    private String sector;

    @Column(name = "COSTALI")
    private Float costAli;

    @Column(name = "STREET1")
    @Size(max=55)
    @NotEmpty
    private String street1;

    @Column(name = "STREET2")
    @Size(max=55)
    @NotEmpty
    private String street2;

    @Column(name = "REFERENCE")
    @Size(max=55)
    @NotEmpty
    private String reference;

    //Relations
    @JsonIgnore
    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private List<House> houseList;

    @JsonIgnore
    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private List<Expenses> expensesList;

    @JsonIgnore
    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private  List<AnnualCounts> annualCountsList;

    @JsonIgnore
    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private List<Owner> ownerList;

    @JoinColumn(name = "IDUSER", referencedColumnName = "IDUSER")
    @ManyToOne
    private USer uSer;

    @JsonIgnore
    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private List<Income> incomeList;

    public Condominium(){
        super();
    }

    private Condominium(Integer idcondominium){
        super();
        this.idcondominium = idcondominium;
    }

    /**/

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public USer getuSer() {
        return uSer;
    }

    public void setuSer(USer uSer) {
        this.uSer = uSer;
    }

    public Integer getIdcondominium() {
        return idcondominium;
    }

    public void setIdcondominium(Integer idcondominium) {
        this.idcondominium = idcondominium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Float getCostAli() {
        return costAli;
    }

    public void setCostAli(Float costAli) {
        this.costAli = costAli;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<House> getHouseList() {
        return houseList;
    }

    public void setHouseList(List<House> houseList) {
        this.houseList = houseList;
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
    }

    public List<AnnualCounts> getAnnualCountsList() {
        return annualCountsList;
    }

    public void setAnnualCountsList(List<AnnualCounts> annualCountsList) {
        this.annualCountsList = annualCountsList;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
}
