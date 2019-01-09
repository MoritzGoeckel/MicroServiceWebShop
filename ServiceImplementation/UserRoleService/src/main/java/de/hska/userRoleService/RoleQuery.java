package de.hska.userRoleService;

public class RoleQuery {

	private String typ;
	private Integer level;
	
	public RoleQuery() {
		
	}
	
	public RoleQuery(String typ, Integer level) {
		this.typ = typ;
		this.level = level;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
