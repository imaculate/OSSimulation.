import java.
public class CPUImpl implements CPU{

	ProcessControlBlock currP;
	//the current process control block.


    int execute(int timeUnits){
	
		int required = currP.getInstruction().getBurstRemaining();
		int used = Math.min(required, timeUnits);
		Simulation.timer.advanceSystemTime(used);
		Simulation.timer.advanceUserTime(used);
		if(required< timeUnits){
			return (timeUnits - required);
		}else{
			return 0;
		}
	
		
	}
    
    /**
     * Switch the current process out and the given process in. 
     * 
     * @return the previously executing process.
     */
    ProcessControlBlock contextSwitch(ProcessControlBlock process){
		currP = process;
	}
    
    /**
     * Obtain the PCB of the currently  executing process
     */
    ProcessControlBlock currentProcesss(){
		return currP;
	}

    /**
     * Determine whether the CPU is idle (<code>currentProcess()==null</code>).
     */
    boolean isIdle(){
	return (currentProcess()==null); 

	}
}
