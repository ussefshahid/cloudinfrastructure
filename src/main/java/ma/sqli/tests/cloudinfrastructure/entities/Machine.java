package ma.sqli.tests.cloudinfrastructure.entities;

import ma.sqli.tests.cloudinfrastructure.state.InactiveMachine;
import ma.sqli.tests.cloudinfrastructure.state.StateMachine;

public class Machine {
	private String name;
	private String operatingSystem;
	private String diskSize;
	private String memory;
	private StateMachine state;
	
	public void startMachine() {
		state.startMachine();
	}
	public void stopMachine() {
		state.stopMachine();
	}
	public Machine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Machine(String name, String operatingSystem, String diskSize, String memory) {
		super();
		this.name = name;
		this.operatingSystem = operatingSystem;
		this.diskSize = diskSize;
		this.memory = memory;
		this.state=new InactiveMachine();
		this.state.setMachine(this);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getDiskSize() {
		return diskSize;
	}
	public void setDiskSize(String diskSize) {
		this.diskSize = diskSize;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public StateMachine getState() {
		return state;
	}
	public void setState(StateMachine state) {
		this.state = state;
		this.state.setMachine(this);
	}
	@Override
	public String toString() {
		return name+":"+state;
	}
	
	@Override
	public boolean equals(Object obj) {
		Machine machine=(Machine) obj;
		return machine.getName().equals(this.name);
	}
}
