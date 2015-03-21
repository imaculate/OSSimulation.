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
   static int numOfProcesses;

	public Process(int ID ){
		this.ID = ID
		
	}
   
   public Process(String name){
      this.name = name;
      this.ID = numOfProcesses++;
   }
    int getPID(){
		return ID;
	}
   
 

    /**
     * Obtain program name.
     * 
     */
    String getProgramName(){
		return name;
	}
    

    /**
     * Obtain current program 'instruction'.
     */
    Instruction getInstruction(){
      return inst[processNumber];
		
		
	}
    
    
    /**
     * Advance to next instruction.
     */
    void nextInstruction(){
      if(processNumber == inst.length-1){
         this.state = STATE.TERMINATED;
      }else{   
         processNumber++;
      }
		
	}
    
    /**
     * Obtain process state.
     */
    State getState(){
		return state;
	}
    
    /**
     * Set process state.
     * Requires <code>getState()!=State.TERMINATED</code>.
     */
    void setState(State state){
		if(getState()!=State.TERMINATED){
	
			this.state = state;
			}
		}

	
	
}
