import java.util.*;

public class CPUImpl implements CPU{
      private int switches;
   ProcessControlBlock currP;
	//the current process control block.


   public int execute(int timeUnits){
      
       
            
   
      int required = ((CPUInstruction)currP.getInstruction()).getBurstRemaining();
      int used = Math.min(required, timeUnits);
   
      Simulation.timer.advanceUserTime(used);
      if(required<= timeUnits){
      
          return (timeUnits - required);  
      }
      else{
          ((CPUInstruction)currP.getInstruction()).setBurstRemaining(required - timeUnits);
                      return 0;
      }
      
   
   	
   }
    
    /**
     * Switch the current process out and the given process in. 
     * 
     * @return the previously executing process.
     */
     
       public int getContextSwiches(){
      return switches;
   }
     
   public void contextSwitch(ProcessControlBlock process){
      switches++;
      String old = "", curr = "";
      if(currP == null){
            old = "{Idle}";
      }else{
         old =curr.toString();
      }
      if(process == null) {
         curr = "{Idle}";
       }else{
         curr = process.toString();
      }
      
      System.out.printf("Time: %010d ContextSwitch(%s)  (%s)\n", Simulation.timer.getSystemTime(), old, curr);
      currP = process;
            Simulation.timer.advanceKernelTime(Simulation.overhead);
              
          //  Time: 0000000000 Context Switch({Idle},{1, TestOne/program1.prg}).

      
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
