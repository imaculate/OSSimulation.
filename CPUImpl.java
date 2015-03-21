import java.util.*;
public class CPUImpl implements CPU{

   ProcessControlBlock currP;
	//the current process control block.


   public int execute(int timeUnits){
      currP.setState(ProcessControlBlock.State.RUNNING);
   
      int required = ((CPUInstruction)currP.getInstruction()).getBurstRemaining();
      int used = Math.min(required, timeUnits);
   
      Simulation.timer.advanceUserTime(used);
      if(required<= timeUnits){
         
         if(((Process)currP).processNumber == (((Process)currP).inst.size())){
            Simulation.kernel.syscall(Kernel.TERMINATE_PROCESS);
            
         }else{
            ((CPUInstruction)currP.getInstruction()).setBurstRemaining(required - timeUnits);
            
           
         }
          return (timeUnits - required);  
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
   public void contextSwitch(ProcessControlBlock process){
      currP = process;
   }
    
    /**
     * Obtain the PCB of the currently  executing process
     */
   public ProcessControlBlock getProcess(){
      return currP;
   }

    /**
     * Determine whether the CPU is idle (<code>currentProcess()==null</code>).
     */
   public boolean isIdle(){
      return (getProcess()==null); 
   
   }
}
