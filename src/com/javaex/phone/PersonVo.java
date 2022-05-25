package com.javaex.phone;

public class PersonVo {
	
	private int personId;
	private String name;
	private String hp;
	private String company;
	
	public PersonVo() {
		
	}
	
	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PersonVo(int personId, String name, String hp, String company) {
		this.personId = personId;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public int getPersoId() {
		return personId;
	}

	public void setPersoId(int persoId) {
		this.personId = persoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public void print() {
		System.out.println(personId +"\t" + name + "\t" + hp + "\t" + company);
	}
}
