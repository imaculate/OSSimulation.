
/**
 * A CPU object records the currently executing process, the 
 * number of context switches that have occurred during execution, and
 * provides for simulated execution of processes.
 * 
 * @author Stephan Jamieson.
 * @version 8/3/15
 */
public interface CPU {

    /**
     * Obtain the currently executing process.
     */
    ProcessControlBlock getCurrentProcess();

    /**
     * Execute the current process in user space for the given number of time units.
     * 
     * If the current cpu burst can complete in the given time, then the CPU will execute 
     * the next instruction in the 'program'. This must be a system call (either I/O or terminate).
     * Either will cause this process to be switched out.
     * 
     * The CPU will update the system timer to indicate the amout of user time spent processing.
     * 
     * The method returns the quantity of unused time unit. A value greater than zero means that 
     * the current cpu burst was completed. A value of zero means the current cpu 
     * burst may or may not have completed.
     * 
     * @return number of unused time units.
     */
    int execute(int timeUnits);
	
	
    
    /**
     * Switch the current process out and the given process in. 
     * 
     * @return the previously executing process.
     */
    ProcessControlBlock contextSwitch(ProcessControlBlock process);
    
    /**
     * Obtain the PCB of the currently  executing process
     */
    ProcessControlBlock currentProcesss();

    /**
     * Determine whether the CPU is idle (<code>currentProcess()==null</code>).
     */
    boolean isIdle();
}
