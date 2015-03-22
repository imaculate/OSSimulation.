import java.util.*;
public class Process implements ProcessControlBlock{
	
/**
 * Abstract Description of Process Control Block used by kernel and simulator.
 * 
 * @author Stephan Jamieson
 * @version 8/3/15
 */


    /**
     * Possible process states.
     */
    //enum State { WAITING, READY, RUNNING, TERMINATED };

    /**
     * Obtain process ID.
     */
   int ID;
   String name;
   State state;
   ArrayList<Instruction> inst;
   int processNumber;//index of current running instruction.
   static int numOfProcesses = 0;

   public Process(int ID ){
      this.ID = ID;
   	
   }
   
   public Process(String name){
      this.name = name;
      this.ID = numOfProcesses++;
      System.out.println("pid" +ID);
      
   }
   public int getID(){
      return ID;
   }
   
 

    /**
     * Obtain program name.
     * 
     */
   public String getProgramName(){
      return name;
   }
    

    /**
     * Obtain current program 'instruction'.
     */
   public Instruction getInstruction(){
      return inst.get(processNumber);
   	
   	
   }
    
    
    /**
     * Advance to next instruction.
     */
   public void nextInstruction(){
      if(processNumber == inst.size()-1){
         this.state = ProcessControlBlock.State.TERMINATED;
      }
      else{   
         processNumber++;
      }
   	
   }
    
    /**
     * Obtain process state.
     */
   public State getState(){
      return state;
   }
    
    /**
     * Set process state.
     * Requires <code>getState()!=State.TERMINATED</code>.
     */
   public void setState(State state){
      if(getState()!=ProcessControlBlock.State.TERMINATED){
      
         this.state = state;
      }
   }
      
   public String toString() {
      return String.format("{%d, %s}", this.getID(), this.getProgramName());
   }

	
	
}
