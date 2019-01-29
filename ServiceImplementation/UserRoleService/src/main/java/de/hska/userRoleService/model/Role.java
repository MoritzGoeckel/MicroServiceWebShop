package de.hska.userRoleService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
    @GeneratedValue
    @Column(name="id")
	private long id;
	
	@Column(name="typ")
	private String typ;
	
	@Column(name="level")
	private Integer level;
	
	public Role() {
		
	}
	
	public Role(String typ, Integer level) {
		this.typ = typ;
		this.level = level;
	}

	public long getId() {
		return this.id;
	}
	
	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
}
