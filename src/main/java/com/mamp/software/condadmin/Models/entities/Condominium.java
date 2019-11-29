package com.mamp.software.condadmin.Models.entities;

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

    @Column(name = "COSTALI")
    @Size(max=55)
    @NotEmpty
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
    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private List<Owner> ownerList;

    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private List<Expenses> expensesList;

    @OneToMany(mappedBy = "condominium", fetch = FetchType.LAZY)
    private  List<AnnualCounts> annualCountsList;

    /**/
    public Condominium(){
        super();
    }

    private Condominium(Integer idcondominium){
        super();
        this.idcondominium = idcondominium;
    }

    /**/
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
    
}
