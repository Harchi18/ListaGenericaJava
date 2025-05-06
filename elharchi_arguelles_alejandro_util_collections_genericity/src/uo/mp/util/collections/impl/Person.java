package uo.mp.util.collections.impl;

public class Person  implements Comparable<Person>{

	private String name;
	private String surname;
	private int age;
	
	
	
	public Person(String name, String surname, int age) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public int compareTo(Person o) {
		int ageComp = this.getAge() - o.getAge();
		if(ageComp == 0) {
			int nameComp = this.getName().compareTo(o.getName());
			if(nameComp == 0) {
				return this.getSurname().compareTo(o.getSurname());
			}else {
				return nameComp;
			}
		} 
		return ageComp;
	}

}
