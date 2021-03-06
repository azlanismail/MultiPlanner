package adapt.multiplan;

import java.util.ArrayList;

import parser.Values;


public class ConfigurationPlanner {

	private Values vm;
	
	//Defining parameters to the stochastic-games model
			String md_probe = "CUR_PROBE";
			String md_maxCS = "MAX_CS";
			String md_maxRT = "MAX_RT";
			String md_maxFR = "MAX_FR";
			
			//String md_goalTQ = "GOAL_TQ";
			String md_goalTY = "GOAL_TY";
			String md_serviceType = "SV_TY";
			String md_serviceFailedId = "SV_FAIL_ID";
			String md_delay = "CUR_DELAY";
			String md_maxDelay = "MAX_DELAY";
			String md_minDelay = "MIN_DELAY";
			
			String md_retry = "RETRY";

			//utility-based decision making
			String md_wg_cs = "WG_CS"; 
			String md_wg_rt = "WG_RT";  
			String md_wg_fr = "WG_FR"; 
			
	private int MaxResource = 0;
	
	private ArrayList<String> nodeIdlist;
			
	public ConfigurationPlanner() {
		vm = new Values();
		this.MaxResource = 8;
		nodeIdlist = new ArrayList<String>();
	}
	
	public void setAppRequirements(int id, int cpuCores, int cpuSpeed, double cpuLoads, int totalMemory, int freeMemory) {
		//set the following:
		
		String md_id = "APP"+id+"_ID";
		String md_cpuCores = "APP"+id+"_CPU_CORES";
		String md_cpuLoads = "APP"+id+"_CPU_LOADS";
		String md_cpuSpeed = "APP"+id+"_CPU_SPEED";
		String md_totalMemory = "APP"+id+"_TOTALMEMORY";
		String md_freeMemory = "APP"+id+"_FREEMEMORY";
		
		
		System.out.println("the received requirements are, id:"+id+", cpu cores:"+cpuCores+", cpu loads:"+cpuLoads+", cpu speed:"+cpuSpeed);
		vm.setValue(md_id, id);
		vm.setValue(md_cpuCores, cpuCores);
		vm.setValue(md_cpuLoads, cpuLoads);
		vm.setValue(md_cpuSpeed, cpuSpeed);
		vm.setValue(md_totalMemory, totalMemory);
		vm.setValue(md_freeMemory, freeMemory);
	}
	
	public void setNodeCapabilities(int id, String name, int cpuCores, int cpuSpeed, double cpuLoads, int totalMemory, int freeMemory, String location){
		//set the following:
		String md_id = "RS"+id+"_ID";
		String md_name = "RS"+id+"_NAME";
		String md_cpuCores = "RS"+id+"_CPU_CORES";
		String md_cpuLoads = "RS"+id+"_CPU_LOADS";
		String md_cpuSpeed = "RS"+id+"_CPU_SPEED";
		String md_totalMemory = "RS"+id+"_TOTALMEMORY";
		String md_freeMemory = "RS"+id+"_FREEMEMORY";
		String md_location = "RS"+id+"_LOCATION";
		
		System.out.println("the received node capabilities are, id:"+id+", name:"+name+", cpu cores:"+cpuCores+", cpu loads:"+cpuLoads+", cpu speed:"+cpuSpeed);
		vm.setValue(md_id, id);
		//vm.setValue(md_name, name);
		vm.setValue(md_cpuCores, cpuCores);
		vm.setValue(md_cpuSpeed, cpuSpeed);
		vm.setValue(md_cpuLoads, cpuLoads);
		vm.setValue(md_totalMemory, totalMemory);
		vm.setValue(md_freeMemory, freeMemory);
		//vm.setValue(md_location, location);
		
		//insert into a list of node id
		nodeIdlist.add(name);
	}
	
		
	public void setConstantsGoalType(int goalType) {
		vm.setValue(md_goalTY, goalType);
	}
	
			
		
	public void setConstantsMaxCost(double maxCS) {
		vm.setValue(md_maxCS, maxCS);
	}
	
	public void setConstantsMaxResponseTime(int maxRT) {
		vm.setValue(md_maxRT, maxRT);
	}
	
	public void setConstantsMaxFailureRate(double maxFR) {
		vm.setValue(md_maxFR, maxFR);
	}
	
	public void setConstantsUtilWeight(double wgCS, double wgRT, double wgFR) {
		vm.setValue(md_wg_cs, wgCS);
		vm.setValue(md_wg_rt, wgRT);
		vm.setValue(md_wg_fr, wgFR);
	}
	
	public Values getDefinedValues() {
		return vm;
	}
	
	public int getMaxResource() {
		return this.MaxResource;
	}
	
	public ArrayList<String> getNodeIdList() {
		return nodeIdlist;
	}
	
	public static void main(String[] args){
		ArrayList<String> nodeid = new ArrayList<String>();
		ConfigurationPlanner conf = new ConfigurationPlanner();
		conf.setAppRequirements(1, 1, 0, 0.3, 3, 3);
		conf.setNodeCapabilities(1, "NODE1", 3, 3, 0.5, 3, 3, "LOC1");
		conf.setNodeCapabilities(1, "NODE2", 3, 3, 0.5, 3, 3, "LOC1");
		conf.setNodeCapabilities(1, "NODE3", 3, 3, 0.5, 3, 3, "LOC1");
		nodeid = conf.getNodeIdList();
		System.out.println("Success");
		System.out.println("the data in node id list: "+nodeid);
	}
	
}
