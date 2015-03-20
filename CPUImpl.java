public class CPUImpl implements CPU{

	ProcessControlBlock currP;
	//the current process control block.


    int execute(int timeUnits){
	
		int required = currP.getInstruction().getBurstRemaining();
		if(required< timeUnits){
			return (timeUnits - required);
		}else{
			return 0;
		}
	//update systemtimer.

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
	return currentProcess()==null; 

	}
}
