package ma.sqli.tests.cloudinfrastructure.entities;

public class Store {
	private String name;
	
	public Store(String name) {
		this.name=name;
	}
	
	public Store() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
