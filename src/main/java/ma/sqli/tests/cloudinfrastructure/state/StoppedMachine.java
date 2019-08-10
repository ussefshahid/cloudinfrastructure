package ma.sqli.tests.cloudinfrastructure.state;

public class StoppedMachine extends StateMachine {

	@Override
	public void startMachine() {
		machine.setState(new StartedMachine());
	}

	@Override
	public void stopMachine() {
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "stopped";
	}

}
