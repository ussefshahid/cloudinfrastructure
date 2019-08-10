package ma.sqli.tests.cloudinfrastructure.state;

import ma.sqli.tests.cloudinfrastructure.entities.Machine;

public abstract class StateMachine {
	protected Machine machine;
	
	public abstract void startMachine();
	public abstract void stopMachine();
	public abstract String toString();
	
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	
}
