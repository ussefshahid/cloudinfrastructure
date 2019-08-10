package ma.sqli.tests.cloudinfrastructure.factory;

import ma.sqli.tests.cloudinfrastructure.entities.Store;

public class StoreFactory {
	public Store getInstance(String name) {
		return new Store(name);
	}
}
