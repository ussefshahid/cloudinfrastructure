package ma.sqli.tests.cloudinfrastructure.factory;

import ma.sqli.tests.cloudinfrastructure.entities.Machine;

public class MachineFactory {
	public Machine getInstance(String name, String op, String diskSize, String memory) {
		return new Machine(name, op, diskSize, memory);
	}
}
