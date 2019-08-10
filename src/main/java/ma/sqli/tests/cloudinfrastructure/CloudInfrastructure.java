package ma.sqli.tests.cloudinfrastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ma.sqli.tests.cloudinfrastructure.entities.File;
import ma.sqli.tests.cloudinfrastructure.entities.Machine;
import ma.sqli.tests.cloudinfrastructure.entities.Store;
import ma.sqli.tests.cloudinfrastructure.exceptions.CreateStoreException;
import ma.sqli.tests.cloudinfrastructure.factory.FileFactory;
import ma.sqli.tests.cloudinfrastructure.factory.MachineFactory;
import ma.sqli.tests.cloudinfrastructure.factory.StoreFactory;
import ma.sqli.tests.cloudinfrastructure.state.InactiveMachine;
import ma.sqli.tests.cloudinfrastructure.state.StartedMachine;

public class CloudInfrastructure {
	private StoreFactory storeFactory;
	private FileFactory fileFactory;
	private MachineFactory machineFactory;
	
	private List<Machine> machines;
	Map<Store, List<File>> stores;
	
	public CloudInfrastructure() {
		stores=new HashMap<>();
		storeFactory=new StoreFactory();
		fileFactory=new FileFactory();
		machineFactory=new MachineFactory();
		machines=new ArrayList<>();
	}
	
	public void createStore(String storeName) {
		if(searchStore(storeName)==null) {
			stores.put(storeFactory.getInstance(storeName), new ArrayList<>());
		}else throw new CreateStoreException();
	}
	
	public void uploadDocument(String storeName, String... files) {
		Store store=searchStore(storeName);
		if(store!=null) {
			Arrays.stream(files)
				.forEach(file->stores.get(store).add(fileFactory.getInstance(file)));
		}
	}
	
	public String listStores() {
		StringBuilder sb=new StringBuilder();
		
		stores.keySet().stream()
			.forEach(store->{
				sb.append(store.getName()+":");
				if(!stores.get(store).isEmpty()) {
					stores.get(store).stream().forEach(file->sb.append(file.getName()+", "));
					sb.delete(sb.length()-2, sb.length());
				}else sb.append("empty");
				sb.append("||");
			});
		
		return sb.delete(sb.length()-2, sb.length()).toString();
	}
	
	private Store searchStore(String storeName) {
		for(Store store: stores.keySet()) if(store.getName().equals(storeName)) return store;
		return null;
	}
	
	public void deleteStore(String storeName) {
		Store store=searchStore(storeName);
		if(stores!=null)
			stores.remove(store);
	}
	
	public void emptyStore(String storeName) {
		Store store=searchStore(storeName);
		if(stores.containsKey(store))
			stores.get(store).clear();
	}
	
	public void createMachine(String name, String op, String diskSize, String memory) {
		machines.add(machineFactory.getInstance(name, op, diskSize, memory));
		
	}
	
	public String listMachines() {
		StringBuilder sb=new StringBuilder();
		
		machines.stream()
			.forEach(machine->sb.append(machine+"||"));
		
		return sb.delete(sb.length()-2, sb.length()).toString();
	}
	
	public void startMachine(String name) {
		machines.forEach(machine->{
			if(machine.getName().equals(name)) machine.startMachine();
		});
	}
	
	public void stopMachine(String machineName) {
		machines.forEach(machine->{
			if(machine.getName().equals(machineName)) machine.stopMachine();
		});
	}
	
	private Machine searchMachine(String machineName) {
		for(Machine machine: machines) if(machine.getName().equals(machineName)) return machine;
		return null;
	}
	
	/**
	 * @param machineName
	 * @return
	 */
	public int usedMemory(String machineName) {
		Machine machine=searchMachine(machineName);
		if(machine!=null) {
			if(machine.getState() instanceof InactiveMachine) return 0;
			else if(machine.getState() instanceof StartedMachine) return Integer.parseInt(machine.getMemory().split("gb")[0]);
		}
		return 0;
	}
	
	public double usedDisk(String name) {
		Machine machine=searchMachine(name);
		Store store=searchStore(name);
		if(machine!=null) return Integer.parseInt(machine.getDiskSize().split("gb")[0]);
		else if(store!=null) return stores.get(store).size()*0.100;
		return 0;
	}
	
	public double globalUsedDisk() {
		double _globalUsedDisk=0.0;
		
		for(Machine machine: machines) _globalUsedDisk+=usedDisk(machine.getName());
		for(Store store: stores.keySet()) _globalUsedDisk+=0.100*stores.get(store).size();
		
		return _globalUsedDisk;
	}
	
	public double globalUsedMemory() {
		double _globalUsedMemory=0.0;
		
		for(Machine machine: machines) _globalUsedMemory+=usedMemory(machine.getName());
		
		return _globalUsedMemory;
	}
}
