package com.mamp.software.condadmin.Models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "OWNER")
public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDOWNER")
    private Integer idowner;

    @Column(name = "NAME")
    @Size(max=55)
    @NotEmpty
    private String name;

    @Column(name = "LASTNAME")
    @Size(max=55)
    @NotEmpty
    private String lastName;

    @Column(name = "IDCARD")
    @Size(max=10)
    @NotEmpty
    private String ci;

    @Column(name = "EMAIL")
    @Size(max=50)
    @NotEmpty
    private String email;

    //Relations
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<House> houselist;

    @JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
    @ManyToOne
    private Condominium condominium;

    /**/
    public Owner() {
		super();
	}
    
	public Owner(Integer idowner) {
		super();
		this.idowner = idowner;
	}

	/**/
	public Integer getIdowner() {
		return idowner;
	}

	public void setIdowner(Integer idowner) {
		this.idowner = idowner;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
