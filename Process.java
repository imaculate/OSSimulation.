public class Process implements ProcessControlBlock{
	 enum State { WAITING, READY, RUNNING, TERMINATED };

    /**
     * Obtain process ID.
     */
    int getPID(){
		
	}

    /**
     * Obtain program name.
     * 
     */
    String getProgramName(){
		
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
		
	}
    
    /**
     * Set process state.
     * Requires <code>getState()!=State.TERMINATED</code>.
     */
    void setState(State state){

	}
	
}
