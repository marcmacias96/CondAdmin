package com.mamp.software.condadmin.Models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
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

    //BITACORA
	@Column(name = "CREADOPOR")
	@Size(max = 35)
	private String creadoPor;

	@Column(name = "CREADOEN")
	private Calendar creadoEn;

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Calendar getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Calendar creadoEn) {
		this.creadoEn = creadoEn;
	}

	@PrePersist
	public void prePersist() {
		creadoEn = Calendar.getInstance();
		SecurityContext context = SecurityContextHolder.getContext();
		creadoPor = context.getAuthentication().getName();
	}

	@Transient
	private Integer condmId;

	@Transient
	private USer Tuser;

    //Relations
	@JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<House> houselist;

	@JoinColumn(name = "IDCONDOM", referencedColumnName = "IDCONDOM")
	@ManyToOne
	private Condominium condominium;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "IDUSER")
	private USer uSer;

    /**/
    public Owner() {
		super();
	}
    
	public Owner(Integer idowner) {
		super();
		this.idowner = idowner;
	}

	public Integer getCondmId() {
		return condmId;
	}

	public void setCondmId(Integer condmId) {
		this.condmId = condmId;
	}

	public Condominium getCondominium() {
		return condominium;
	}

	public void setCondominium(Condominium condominium) {
		this.condominium = condominium;
	}

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

	public List<House> getHouselist() {
		return houselist;
	}

	public void setHouselist(List<House> houselist) {
		this.houselist = houselist;
	}

	public USer getuSer() {
		return uSer;
	}

	public void setuSer(USer uSer) {
		this.uSer = uSer;
	}

	public USer getTuser() {
		return Tuser;
	}

	public void setTuser(USer tuser) {
		Tuser = tuser;
	}
}
