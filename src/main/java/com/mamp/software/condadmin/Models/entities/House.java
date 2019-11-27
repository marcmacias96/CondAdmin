package com.mamp.software.condadmin.Models.entities;

import javax.persistence.*;
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
    @Size(max=5)
    private Integer number;

    @Column(name = "BLOCK")
    @Size(max=5)
    private String block;

    //RELATIONS
    @JoinColumn(name = "IDOWNER", referencedColumnName = "IDOWNER")
    @ManyToOne
    private Owner owner;

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

    
}
