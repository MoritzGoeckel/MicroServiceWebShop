package de.hska.userRoleService;

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
	private int level;
	
	

	public long getId() {
		return this.id;
	}
	
	public String getTyp() {
		return this.typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
