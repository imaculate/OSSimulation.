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

	public Process(int ID, String name ){
		this.ID = ID; 
		this.name = name;
		
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
		
		
	}
    
    
    /**
     * Advance to next instruction.
     */
    void nextInstruction(){
		
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
