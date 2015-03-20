public class LoadProgramEvent extends Event{
	    private String progName;
    
    public LoadProgramEvent(long arrivalTime, String progName) {
        super(arrivalTime);
        this.progName=progName;
    }
        
    
    /**
     * Obtain the name of the program that must be run.
     */
    public String getProgramName() {
        return progName;
    }
    
    public String toString() { return "LoadProgramEvent("+this.getTime()+", "+this.getProgramName()+")"; }
	
}
