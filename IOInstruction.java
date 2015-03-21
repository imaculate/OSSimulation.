
/**
 * Abstract representation of a block of program code describing an I/O burst.
 * 
 * @author Stephan Jamieson
 * @version 8/3/15
 */
public class IOInstruction extends Instruction {

    private int deviceID;
    
    /**
     * Create an IOInstruction of the given duration for the given process and IO device.
     */
    public IOInstruction(int duration, ProcessControlBlock parent, int deviceID) {
        super(duration, parent);
        this.deviceID = deviceID;
    }
    
    /**
     * Get the ID of the IO device.
     */
    public int getDeviceID() { return deviceID; }
    
    public int getDuration(){
      return duration;
    }
    

}
