
/**
 * Abstract Description of Process Control Block used by kernel and simulator.
 * 
 * @author Stephan Jamieson
 * @version 8/3/15
 */
public interface ProcessControlBlock {

    /**
     * Possible process states.
     */
    enum State { WAITING, READY, RUNNING, TERMINATED };

    /**
     * Obtain process ID.
     */
    int getID();

    /**
     * Obtain program name.
     * 
     */
    String getProgramName();
    

    /**
     * Obtain current program 'instruction'.
     */
    Instruction getInstruction();
    
    
    /**
     * Advance to next instruction.
     */
    void nextInstruction();
    
    /**
     * Obtain process state.
     */
    State getState();
    
    /**
     * Set process state.
     * Requires <code>getState()!=State.TERMINATED</code>.
     */
    void setState(State state);
}
