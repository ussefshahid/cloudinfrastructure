package ma.sqli.tests.cloudinfrastructure.state;

import ma.sqli.tests.cloudinfrastructure.exceptions.MachineStateException;

public class StartedMachine extends StateMachine {

	@Override
	public void startMachine() {
		throw new MachineStateException();
	}

	@Override
	public void stopMachine() {
		machine.setState(new StoppedMachine());
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "running";
	}

}
