
/**
 * An Execve event represents the creation of a program execution.
 * 
 * @author Stephan Jamieson
 * @version 8/3/15
 */
public class ExecveEvent extends Event {

    private String progName;
    
    public ExecveEvent(long startTime, String progName) {
        super(startTime);
        this.progName=progName;
    }
        
    
    /**
     * Obtain the name of the program that must be run.
     */
    public String getProgramName() {
        return progName;
    }
    
    public String toString() { return "ExecveEvent("+this.getTime()+", "+this.getProgramName()+")"; }

}
