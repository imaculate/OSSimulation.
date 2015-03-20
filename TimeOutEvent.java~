
/**
 * A timeout event is used to flag the end of the current execution time slice.
 * 
 * @author Stephan Jamieson
 * @version 8/3/15
 */
public class TimeOutEvent extends Event {

    private ProcessControlBlock process;
    
    /**
     * Create a TimeOut event to mark the end of the execution timeslice for the given process.
     */
    public TimeOutEvent(long systemTime, ProcessControlBlock process) {
        super(systemTime);
        this.process=process;
    }
    
    /**
     * Obtain the process to switched out as a result of this execution timeout.
     */
    public ProcessControlBlock getProcess() { return process; }
        
}
