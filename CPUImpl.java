import java.util.*;
public class CPUImpl implements CPU{

   ProcessControlBlock currP;
	//the current process control block.


   int execute(int timeUnits){
      currP.setState(STATE.RUNNING);
   
      int required = currP.getInstruction().getBurstRemaining();
      int used = Math.min(required, timeUnits);
   
      Simulation.timer.advanceUserTime(used);
      if(required<= timeUnits){
         return (timeUnits - required);
         if(currP.processNumber == (currP.inst.size())){
            Simulation.kernel.syscall(TERMINATE_PROCESS);
         }
            
      }
      else{
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
   ProcessControlBlock currentProcess(){
      return currP;
   }

    /**
     * Determine whether the CPU is idle (<code>currentProcess()==null</code>).
     */
   boolean isIdle(){
      return (currentProcess()==null); 
   
   }
}
