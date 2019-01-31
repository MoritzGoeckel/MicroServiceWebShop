package hska.vslab.sec.sprindsecauthserver;


public class Role {

	private long id;
	
	private String typ;
	
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
