package Person;

import java.util.Objects;

public abstract class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {		
		this.name = name;
		this.age = age;
	}
	
	public Person(Person person)
	{
		this.name = person.name;
		this.age = person.age;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(age, name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
	
	
}
