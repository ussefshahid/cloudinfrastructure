package ma.sqli.tests.cloudinfrastructure.factory;

import ma.sqli.tests.cloudinfrastructure.entities.File;

public class FileFactory {
	public File getInstance(String name) {
		return new File(name);
	}
}
