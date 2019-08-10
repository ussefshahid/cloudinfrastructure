package ma.sqli.tests.cloudinfrastructure.entities;

public class File {
	private String name;

	public File(String name) {
		super();
		this.name = name;
	}

	public File() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
